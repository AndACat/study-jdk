/*
 * Copyright (c) 2000, 2023, Oracle and/or its affiliates. All rights reserved.
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

// -- This file was mechanically generated: Do not edit! -- //

package java.nio;

import java.io.FileDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.ref.Reference;
import java.util.Objects;
import jdk.internal.foreign.MemorySessionImpl;
import jdk.internal.misc.ScopedMemoryAccess.ScopedAccessError;
import jdk.internal.misc.VM;
import jdk.internal.ref.Cleaner;
import sun.nio.ch.DirectBuffer;





final

class DirectByteBufferR



    extends DirectByteBuffer

    implements DirectBuffer



{














































    // Primary constructor
    //
    DirectByteBufferR(int cap) {                   // package-private































        super(cap);
        this.isReadOnly = true;

    }



















































    // For memory-mapped buffers -- invoked by FileChannelImpl via reflection
    //
    protected DirectByteBufferR(int cap, long addr,
                                     FileDescriptor fd,
                                     Runnable unmapper,
                                     boolean isSync, MemorySegment segment)
    {






        super(cap, addr, fd, unmapper, isSync, segment);
        this.isReadOnly = true;

    }



    // For duplicates and slices
    //
    DirectByteBufferR(DirectBuffer db,         // package-private
                               int mark, int pos, int lim, int cap, int off,

                               FileDescriptor fd, boolean isSync,

                               MemorySegment segment)
    {













        super(db, mark, pos, lim, cap, off,

              fd, isSync,

              segment);
        this.isReadOnly = true;

    }

    @Override
    Object base() {
        return null;
    }

    public MappedByteBuffer slice() {
        int pos = this.position();
        int lim = this.limit();
        int rem = (pos <= lim ? lim - pos : 0);
        int off = (pos << 0);
        assert (off >= 0);
        return new DirectByteBufferR(this,
                                              -1,
                                              0,
                                              rem,
                                              rem,
                                              off,

                                              fileDescriptor(),
                                              isSync(),

                                              segment);
    }

    @Override
    public MappedByteBuffer slice(int index, int length) {
        Objects.checkFromIndexSize(index, length, limit());
        return new DirectByteBufferR(this,
                                              -1,
                                              0,
                                              length,
                                              length,
                                              index << 0,

                                              fileDescriptor(),
                                              isSync(),

                                              segment);
    }

    public MappedByteBuffer duplicate() {
        return new DirectByteBufferR(this,
                                              this.markValue(),
                                              this.position(),
                                              this.limit(),
                                              this.capacity(),
                                              0,

                                              fileDescriptor(),
                                              isSync(),

                                              segment);
    }

    public ByteBuffer asReadOnlyBuffer() {













        return duplicate();

    }













































    public ByteBuffer put(byte x) {








        throw new ReadOnlyBufferException();

    }

    public ByteBuffer put(int i, byte x) {








        throw new ReadOnlyBufferException();

    }

    public MappedByteBuffer compact() {

















        throw new ReadOnlyBufferException();

    }

    public boolean isDirect() {
        return true;
    }

    public boolean isReadOnly() {
        return true;
    }



























































































































































    private ByteBuffer putChar(long a, char x) {









        throw new ReadOnlyBufferException();

    }

    public ByteBuffer putChar(char x) {




        throw new ReadOnlyBufferException();

    }

    public ByteBuffer putChar(int i, char x) {




        throw new ReadOnlyBufferException();

    }

    public CharBuffer asCharBuffer() {
        int off = this.position();
        int lim = this.limit();
        assert (off <= lim);
        int rem = (off <= lim ? lim - off : 0);

        int size = rem >> 1;
        if (!UNALIGNED && ((address + off) % (1 << 1) != 0)) {
            return (bigEndian
                    ? (CharBuffer)(new ByteBufferAsCharBufferRB(this,
                                                                       -1,
                                                                       0,
                                                                       size,
                                                                       size,
                                                                       address + off, segment))
                    : (CharBuffer)(new ByteBufferAsCharBufferRL(this,
                                                                       -1,
                                                                       0,
                                                                       size,
                                                                       size,
                                                                       address + off, segment)));
        } else {
            return (nativeByteOrder
                    ? (CharBuffer)(new DirectCharBufferRU(this,
                                                                 -1,
                                                                 0,
                                                                 size,
                                                                 size,
                                                                 off, segment))
                    : (CharBuffer)(new DirectCharBufferRS(this,
                                                                 -1,
                                                                 0,
                                                                 size,
                                                                 size,
                                                                 off, segment)));
        }
    }































    private ByteBuffer putShort(long a, short x) {









        throw new ReadOnlyBufferException();

    }

    public ByteBuffer putShort(short x) {




        throw new ReadOnlyBufferException();

    }

    public ByteBuffer putShort(int i, short x) {




        throw new ReadOnlyBufferException();

    }

    public ShortBuffer asShortBuffer() {
        int off = this.position();
        int lim = this.limit();
        assert (off <= lim);
        int rem = (off <= lim ? lim - off : 0);

        int size = rem >> 1;
        if (!UNALIGNED && ((address + off) % (1 << 1) != 0)) {
            return (bigEndian
                    ? (ShortBuffer)(new ByteBufferAsShortBufferRB(this,
                                                                       -1,
                                                                       0,
                                                                       size,
                                                                       size,
                                                                       address + off, segment))
                    : (ShortBuffer)(new ByteBufferAsShortBufferRL(this,
                                                                       -1,
                                                                       0,
                                                                       size,
                                                                       size,
                                                                       address + off, segment)));
        } else {
            return (nativeByteOrder
                    ? (ShortBuffer)(new DirectShortBufferRU(this,
                                                                 -1,
                                                                 0,
                                                                 size,
                                                                 size,
                                                                 off, segment))
                    : (ShortBuffer)(new DirectShortBufferRS(this,
                                                                 -1,
                                                                 0,
                                                                 size,
                                                                 size,
                                                                 off, segment)));
        }
    }































    private ByteBuffer putInt(long a, int x) {









        throw new ReadOnlyBufferException();

    }

    public ByteBuffer putInt(int x) {




        throw new ReadOnlyBufferException();

    }

    public ByteBuffer putInt(int i, int x) {




        throw new ReadOnlyBufferException();

    }

    public IntBuffer asIntBuffer() {
        int off = this.position();
        int lim = this.limit();
        assert (off <= lim);
        int rem = (off <= lim ? lim - off : 0);

        int size = rem >> 2;
        if (!UNALIGNED && ((address + off) % (1 << 2) != 0)) {
            return (bigEndian
                    ? (IntBuffer)(new ByteBufferAsIntBufferRB(this,
                                                                       -1,
                                                                       0,
                                                                       size,
                                                                       size,
                                                                       address + off, segment))
                    : (IntBuffer)(new ByteBufferAsIntBufferRL(this,
                                                                       -1,
                                                                       0,
                                                                       size,
                                                                       size,
                                                                       address + off, segment)));
        } else {
            return (nativeByteOrder
                    ? (IntBuffer)(new DirectIntBufferRU(this,
                                                                 -1,
                                                                 0,
                                                                 size,
                                                                 size,
                                                                 off, segment))
                    : (IntBuffer)(new DirectIntBufferRS(this,
                                                                 -1,
                                                                 0,
                                                                 size,
                                                                 size,
                                                                 off, segment)));
        }
    }































    private ByteBuffer putLong(long a, long x) {









        throw new ReadOnlyBufferException();

    }

    public ByteBuffer putLong(long x) {




        throw new ReadOnlyBufferException();

    }

    public ByteBuffer putLong(int i, long x) {




        throw new ReadOnlyBufferException();

    }

    public LongBuffer asLongBuffer() {
        int off = this.position();
        int lim = this.limit();
        assert (off <= lim);
        int rem = (off <= lim ? lim - off : 0);

        int size = rem >> 3;
        if (!UNALIGNED && ((address + off) % (1 << 3) != 0)) {
            return (bigEndian
                    ? (LongBuffer)(new ByteBufferAsLongBufferRB(this,
                                                                       -1,
                                                                       0,
                                                                       size,
                                                                       size,
                                                                       address + off, segment))
                    : (LongBuffer)(new ByteBufferAsLongBufferRL(this,
                                                                       -1,
                                                                       0,
                                                                       size,
                                                                       size,
                                                                       address + off, segment)));
        } else {
            return (nativeByteOrder
                    ? (LongBuffer)(new DirectLongBufferRU(this,
                                                                 -1,
                                                                 0,
                                                                 size,
                                                                 size,
                                                                 off, segment))
                    : (LongBuffer)(new DirectLongBufferRS(this,
                                                                 -1,
                                                                 0,
                                                                 size,
                                                                 size,
                                                                 off, segment)));
        }
    }































    private ByteBuffer putFloat(long a, float x) {









        throw new ReadOnlyBufferException();

    }

    public ByteBuffer putFloat(float x) {




        throw new ReadOnlyBufferException();

    }

    public ByteBuffer putFloat(int i, float x) {




        throw new ReadOnlyBufferException();

    }

    public FloatBuffer asFloatBuffer() {
        int off = this.position();
        int lim = this.limit();
        assert (off <= lim);
        int rem = (off <= lim ? lim - off : 0);

        int size = rem >> 2;
        if (!UNALIGNED && ((address + off) % (1 << 2) != 0)) {
            return (bigEndian
                    ? (FloatBuffer)(new ByteBufferAsFloatBufferRB(this,
                                                                       -1,
                                                                       0,
                                                                       size,
                                                                       size,
                                                                       address + off, segment))
                    : (FloatBuffer)(new ByteBufferAsFloatBufferRL(this,
                                                                       -1,
                                                                       0,
                                                                       size,
                                                                       size,
                                                                       address + off, segment)));
        } else {
            return (nativeByteOrder
                    ? (FloatBuffer)(new DirectFloatBufferRU(this,
                                                                 -1,
                                                                 0,
                                                                 size,
                                                                 size,
                                                                 off, segment))
                    : (FloatBuffer)(new DirectFloatBufferRS(this,
                                                                 -1,
                                                                 0,
                                                                 size,
                                                                 size,
                                                                 off, segment)));
        }
    }































    private ByteBuffer putDouble(long a, double x) {









        throw new ReadOnlyBufferException();

    }

    public ByteBuffer putDouble(double x) {




        throw new ReadOnlyBufferException();

    }

    public ByteBuffer putDouble(int i, double x) {




        throw new ReadOnlyBufferException();

    }

    public DoubleBuffer asDoubleBuffer() {
        int off = this.position();
        int lim = this.limit();
        assert (off <= lim);
        int rem = (off <= lim ? lim - off : 0);

        int size = rem >> 3;
        if (!UNALIGNED && ((address + off) % (1 << 3) != 0)) {
            return (bigEndian
                    ? (DoubleBuffer)(new ByteBufferAsDoubleBufferRB(this,
                                                                       -1,
                                                                       0,
                                                                       size,
                                                                       size,
                                                                       address + off, segment))
                    : (DoubleBuffer)(new ByteBufferAsDoubleBufferRL(this,
                                                                       -1,
                                                                       0,
                                                                       size,
                                                                       size,
                                                                       address + off, segment)));
        } else {
            return (nativeByteOrder
                    ? (DoubleBuffer)(new DirectDoubleBufferRU(this,
                                                                 -1,
                                                                 0,
                                                                 size,
                                                                 size,
                                                                 off, segment))
                    : (DoubleBuffer)(new DirectDoubleBufferRS(this,
                                                                 -1,
                                                                 0,
                                                                 size,
                                                                 size,
                                                                 off, segment)));
        }
    }

}
