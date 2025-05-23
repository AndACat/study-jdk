/*
 * Copyright (c) 2002, 2023, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package sun.nio.cs.ext;

import jdk.internal.access.JavaLangAccess;
import jdk.internal.access.SharedSecrets;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import sun.nio.cs.HistoricallyNamedCharset;
import sun.nio.cs.DelegatableDecoder;
import sun.nio.cs.DoubleByte;
import sun.nio.cs.Surrogate;
import sun.nio.cs.SingleByte;
import sun.nio.cs.*;
import static sun.nio.cs.CharsetMapping.*;

public class EUC_JP
    extends Charset
    implements HistoricallyNamedCharset
{
    private static final JavaLangAccess JLA = SharedSecrets.getJavaLangAccess();

    public EUC_JP() {
        super("EUC-JP",  ExtendedCharsets.aliasesFor("EUC-JP"));
    }

    public String historicalName() {
        return "EUC_JP";
    }

    public boolean contains(Charset cs) {
        return ((cs.name().equals("US-ASCII"))
                || (cs instanceof JIS_X_0201)
                || (cs instanceof JIS_X_0208)
                || (cs instanceof JIS_X_0212)
                || (cs instanceof EUC_JP));
    }

    public CharsetDecoder newDecoder() {
        return new Decoder(this);
    }

    public CharsetEncoder newEncoder() {
        return new Encoder(this);
    }

    public static class Decoder extends CharsetDecoder
        implements DelegatableDecoder {

        public static final SingleByte.Decoder DEC0201 =
            (SingleByte.Decoder)new JIS_X_0201().newDecoder();

        public static final DoubleByte.Decoder DEC0208 =
            (DoubleByte.Decoder)new JIS_X_0208().newDecoder();

        public static final DoubleByte.Decoder DEC0212 =
            (DoubleByte.Decoder)new JIS_X_0212().newDecoder();

        private final SingleByte.Decoder dec0201;
        private final DoubleByte.Decoder dec0208;
        private final DoubleByte.Decoder dec0212;

        protected Decoder(Charset cs) {
            this(cs, 0.5f, 1.0f, DEC0201, DEC0208, DEC0212);
        }

        protected Decoder(Charset cs, float avgCpb, float maxCpb,
                          SingleByte.Decoder dec0201,
                          DoubleByte.Decoder dec0208,
                          DoubleByte.Decoder dec0212) {
            super(cs, avgCpb, maxCpb);
            this.dec0201 = dec0201;
            this.dec0208 = dec0208;
            this.dec0212 = dec0212;
        }


        protected char decodeDouble(int byte1, int byte2) {
            if (byte1 == 0x8e) {
                if (byte2 < 0x80)
                    return UNMAPPABLE_DECODING;
                return dec0201.decode((byte)byte2);
            }
            return dec0208.decodeDouble(byte1 - 0x80, byte2 - 0x80);
        }

        private CoderResult decodeArrayLoop(ByteBuffer src,
                                            CharBuffer dst)
        {
            byte[] sa = src.array();
            int sp = src.arrayOffset() + src.position();
            int sl = src.arrayOffset() + src.limit();

            char[] da = dst.array();
            int dp = dst.arrayOffset() + dst.position();
            int dl = dst.arrayOffset() + dst.limit();

            int b1 = 0, b2 = 0;
            int inputSize = 0;
            char outputChar = UNMAPPABLE_DECODING;
            try {
                while (sp < sl) {
                    b1 = sa[sp] & 0xff;
                    inputSize = 1;

                    if ((b1 & 0x80) == 0) {
                        outputChar = (char)b1;
                    } else {                        // Multibyte char
                        if (b1 == 0x8f) {           // JIS0212
                            if (sp + 3 > sl)
                               return CoderResult.UNDERFLOW;
                            b1 = sa[sp + 1] & 0xff;
                            b2 = sa[sp + 2] & 0xff;
                            inputSize += 2;
                            if (dec0212 == null)    // JIS02012 not supported
                                return CoderResult.unmappableForLength(inputSize);
                            outputChar = dec0212.decodeDouble(b1-0x80, b2-0x80);
                        } else {                     // JIS0201, JIS0208
                            if (sp + 2 > sl)
                               return CoderResult.UNDERFLOW;
                            b2 = sa[sp + 1] & 0xff;
                            inputSize++;
                            outputChar = decodeDouble(b1, b2);
                        }
                    }
                    if (outputChar == UNMAPPABLE_DECODING) { // can't be decoded
                        return CoderResult.unmappableForLength(inputSize);
                    }
                    if (dp + 1 > dl)
                        return CoderResult.OVERFLOW;
                    da[dp++] = outputChar;
                    sp += inputSize;
                }
                return CoderResult.UNDERFLOW;
            } finally {
                src.position(sp - src.arrayOffset());
                dst.position(dp - dst.arrayOffset());
            }
        }

        private CoderResult decodeBufferLoop(ByteBuffer src,
                                             CharBuffer dst)
        {
            int mark = src.position();
            int b1 = 0, b2 = 0;
            int inputSize = 0;
            char outputChar = UNMAPPABLE_DECODING;

            try {
                while (src.hasRemaining()) {
                    b1 = src.get() & 0xff;
                    inputSize = 1;
                    if ((b1 & 0x80) == 0) {
                        outputChar = (char)b1;
                    } else {                         // Multibyte char
                        if (b1 == 0x8f) {   // JIS0212
                            if (src.remaining() < 2)
                               return CoderResult.UNDERFLOW;
                            b1 = src.get() & 0xff;
                            b2 = src.get() & 0xff;
                            inputSize += 2;
                            if (dec0212 == null)    // JIS02012 not supported
                                return CoderResult.unmappableForLength(inputSize);
                            outputChar = dec0212.decodeDouble(b1-0x80, b2-0x80);
                        } else {                     // JIS0201 JIS0208
                            if (src.remaining() < 1)
                               return CoderResult.UNDERFLOW;
                            b2 = src.get() & 0xff;
                            inputSize++;
                            outputChar = decodeDouble(b1, b2);
                        }
                    }
                    if (outputChar == UNMAPPABLE_DECODING) {
                        return CoderResult.unmappableForLength(inputSize);
                    }
                if (dst.remaining() < 1)
                    return CoderResult.OVERFLOW;
                dst.put(outputChar);
                mark += inputSize;
                }
                return CoderResult.UNDERFLOW;
            } finally {
                src.position(mark);
            }
        }

        // Make some protected methods public for use by JISAutoDetect
        public CoderResult decodeLoop(ByteBuffer src, CharBuffer dst) {
            if (src.hasArray() && dst.hasArray())
                return decodeArrayLoop(src, dst);
            else
                return decodeBufferLoop(src, dst);
        }
        public void implReset() {
            super.implReset();
        }
        public CoderResult implFlush(CharBuffer out) {
            return super.implFlush(out);
        }
    }


    public static class Encoder extends CharsetEncoder {

        static final SingleByte.Encoder ENC0201 =
            (SingleByte.Encoder)new JIS_X_0201().newEncoder();

        static final DoubleByte.Encoder ENC0208 =
            (DoubleByte.Encoder)new JIS_X_0208().newEncoder();

        static final DoubleByte.Encoder ENC0212 =
            (DoubleByte.Encoder)new JIS_X_0212().newEncoder();

        private final Surrogate.Parser sgp = new Surrogate.Parser();


        private final SingleByte.Encoder enc0201;
        private final DoubleByte.Encoder enc0208;
        private final DoubleByte.Encoder enc0212;

        protected Encoder(Charset cs) {
            this(cs, 3.0f, 3.0f, ENC0201, ENC0208, ENC0212);
        }

        protected Encoder(Charset cs, float avgBpc, float maxBpc,
                          SingleByte.Encoder enc0201,
                          DoubleByte.Encoder enc0208,
                          DoubleByte.Encoder enc0212) {
            super(cs, avgBpc, maxBpc);
            this.enc0201 = enc0201;
            this.enc0208 = enc0208;
            this.enc0212 = enc0212;
        }

        public boolean canEncode(char c) {
            byte[]  encodedBytes = new byte[3];
            return encodeSingle(c, encodedBytes) != 0 ||
                   encodeDouble(c) != UNMAPPABLE_ENCODING;
        }

        protected int encodeSingle(char inputChar, byte[] outputByte) {
            int b = enc0201.encode(inputChar);
            if (b == UNMAPPABLE_ENCODING)
                return 0;
            if (b >= 0 && b < 128) {
                outputByte[0] = (byte)b;
                return 1;
            }
            outputByte[0] = (byte)0x8e;
            outputByte[1] = (byte)b;
            return 2;
        }

        protected int encodeDouble(char ch) {
            int b = enc0208.encodeChar(ch);
            if (b != UNMAPPABLE_ENCODING)
                return b + 0x8080;
            if (enc0212 != null) {
                b = enc0212.encodeChar(ch);
                if (b != UNMAPPABLE_ENCODING)
                    b += 0x8F8080;
            }
            return b;
        }

        private CoderResult encodeArrayLoop(CharBuffer src,
                                            ByteBuffer dst)
        {
            char[] sa = src.array();
            int sp = src.arrayOffset() + src.position();
            int sl = src.arrayOffset() + src.limit();

            byte[] da = dst.array();
            int dp = dst.arrayOffset() + dst.position();
            int dl = dst.arrayOffset() + dst.limit();

            int outputSize = 0;
            byte[]  outputByte;
            int     inputSize = 0;                 // Size of input
            byte[]  tmpBuf = new byte[3];

            try {
                if (enc0201.isASCIICompatible()) {
                    int n = JLA.encodeASCII(sa, sp, da, dp, Math.min(dl - dp, sl - sp));
                    sp += n;
                    dp += n;
                }
                while (sp < sl) {
                    outputByte = tmpBuf;
                    char c = sa[sp];
                    if (Character.isSurrogate(c)) {
                        if (sgp.parse(c, sa, sp, sl) < 0)
                            return sgp.error();
                        return sgp.unmappableResult();
                    }
                    outputSize = encodeSingle(c, outputByte);
                    if (outputSize == 0) { // DoubleByte
                        int ncode = encodeDouble(c);
                        if (ncode != UNMAPPABLE_ENCODING) {
                            if ((ncode & 0xFF0000) == 0) {
                                outputByte[0] = (byte) ((ncode & 0xff00) >> 8);
                                outputByte[1] = (byte) (ncode & 0xff);
                                outputSize = 2;
                            } else {
                                outputByte[0] = (byte) 0x8f;
                                outputByte[1] = (byte) ((ncode & 0xff00) >> 8);
                                outputByte[2] = (byte) (ncode & 0xff);
                                outputSize = 3;
                            }
                        } else {
                            return CoderResult.unmappableForLength(1);
                        }
                    }
                    if (dl - dp < outputSize)
                        return CoderResult.OVERFLOW;
                    // Put the byte in the output buffer
                    for (int i = 0; i < outputSize; i++) {
                        da[dp++] = outputByte[i];
                    }
                    sp++;
                }
                return CoderResult.UNDERFLOW;
            } finally {
                src.position(sp - src.arrayOffset());
                dst.position(dp - dst.arrayOffset());
            }
        }

        private CoderResult encodeBufferLoop(CharBuffer src,
                                             ByteBuffer dst)
        {
            int outputSize = 0;
            byte[]  outputByte;
            int     inputSize = 0;                 // Size of input
            byte[]  tmpBuf = new byte[3];

            int mark = src.position();

            try {
                while (src.hasRemaining()) {
                    outputByte = tmpBuf;
                    char c = src.get();
                    if (Character.isSurrogate(c)) {
                        if (sgp.parse(c, src) < 0)
                            return sgp.error();
                        return sgp.unmappableResult();
                    }
                    outputSize = encodeSingle(c, outputByte);
                    if (outputSize == 0) { // DoubleByte
                        int ncode = encodeDouble(c);
                        if (ncode != UNMAPPABLE_ENCODING) {
                            if ((ncode & 0xFF0000) == 0) {
                                outputByte[0] = (byte) ((ncode & 0xff00) >> 8);
                                outputByte[1] = (byte) (ncode & 0xff);
                                outputSize = 2;
                            } else {
                                outputByte[0] = (byte) 0x8f;
                                outputByte[1] = (byte) ((ncode & 0xff00) >> 8);
                                outputByte[2] = (byte) (ncode & 0xff);
                                outputSize = 3;
                            }
                        } else {
                            return CoderResult.unmappableForLength(1);
                        }
                    }
                    if (dst.remaining() < outputSize)
                        return CoderResult.OVERFLOW;
                    // Put the byte in the output buffer
                    for (int i = 0; i < outputSize; i++) {
                        dst.put(outputByte[i]);
                    }
                    mark++;
                }
                return CoderResult.UNDERFLOW;
            } finally {
                src.position(mark);
            }
        }

        protected CoderResult encodeLoop(CharBuffer src,
                                         ByteBuffer dst)
        {
            if (src.hasArray() && dst.hasArray())
                return encodeArrayLoop(src, dst);
            else
                return encodeBufferLoop(src, dst);
        }
    }
}
