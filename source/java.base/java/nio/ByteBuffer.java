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




import java.lang.ref.Reference;






import java.lang.foreign.MemorySegment;
import java.util.Objects;
import jdk.internal.util.ArraysSupport;

/**
 * A byte buffer.
 *
 * <p> This class defines six categories of operations upon
 * byte buffers:
 *
 * <ul>
 *
 *   <li><p> Absolute and relative {@link #get() <i>get</i>} and
 *   {@link #put(byte) <i>put</i>} methods that read and write
 *   single bytes; </p></li>
 *
 *   <li><p> Absolute and relative {@link #get(byte[]) <i>bulk get</i>}
 *   methods that transfer contiguous sequences of bytes from this buffer
 *   into an array;</p></li>
 *
 *   <li><p> Absolute and relative {@link #put(byte[]) <i>bulk put</i>}
 *   methods that transfer contiguous sequences of bytes from a
 *   byte array or some other byte
 *   buffer into this buffer;</p></li>
 *

 *
 *   <li><p> Absolute and relative {@link #getChar() <i>get</i>}
 *   and {@link #putChar(char) <i>put</i>} methods that read and
 *   write values of other primitive types, translating them to and from
 *   sequences of bytes in a particular byte order; </p></li>
 *
 *   <li><p> Methods for creating <i><a href="#views">view buffers</a></i>,
 *   which allow a byte buffer to be viewed as a buffer containing values of
 *   some other primitive type; and </p></li>
 *

 *
 *   <li><p> A method for {@link #compact compacting}
 *   a byte buffer.  </p></li>
 *
 * </ul>
 *
 * <p> Byte buffers can be created either by {@link #allocate
 * <i>allocation</i>}, which allocates space for the buffer's
 *

 *
 * content, or by {@link #wrap(byte[]) <i>wrapping</i>} an
 * existing byte array into a buffer.
 *







 *

 *
 * <a id="direct"></a>
 * <h2> Direct <i>vs.</i> non-direct buffers </h2>
 *
 * <p> A byte buffer is either <i>direct</i> or <i>non-direct</i>.  Given a
 * direct byte buffer, the Java virtual machine will make a best effort to
 * perform native I/O operations directly upon it.  That is, it will attempt to
 * avoid copying the buffer's content to (or from) an intermediate buffer
 * before (or after) each invocation of one of the underlying operating
 * system's native I/O operations.
 *
 * <p> A direct byte buffer may be created by invoking the {@link
 * #allocateDirect(int) allocateDirect} factory method of this class.  The
 * buffers returned by this method typically have somewhat higher allocation
 * and deallocation costs than non-direct buffers.  The contents of direct
 * buffers may reside outside of the normal garbage-collected heap, and so
 * their impact upon the memory footprint of an application might not be
 * obvious.  It is therefore recommended that direct buffers be allocated
 * primarily for large, long-lived buffers that are subject to the underlying
 * system's native I/O operations.  In general it is best to allocate direct
 * buffers only when they yield a measurable gain in program performance.
 *
 * <p> A direct byte buffer may also be created by {@link
 * java.nio.channels.FileChannel#map mapping} a region of a file
 * directly into memory.  An implementation of the Java platform may optionally
 * support the creation of direct byte buffers from native code via JNI.  If an
 * instance of one of these kinds of buffers refers to an inaccessible region
 * of memory then an attempt to access that region will not change the buffer's
 * content and will cause an unspecified exception to be thrown either at the
 * time of the access or at some later time.
 *
 * <p> Whether a byte buffer is direct or non-direct may be determined by
 * invoking its {@link #isDirect isDirect} method.  This method is provided so
 * that explicit buffer management can be done in performance-critical code.
 *
 *
 * <a id="bin"></a>
 * <h2> Access to binary data </h2>
 *
 * <p> This class defines methods for reading and writing values of all other
 * primitive types, except {@code boolean}.  Primitive values are translated
 * to (or from) sequences of bytes according to the buffer's current byte
 * order, which may be retrieved and modified via the {@link #order order}
 * methods.  Specific byte orders are represented by instances of the {@link
 * ByteOrder} class.  The initial order of a byte buffer is always {@link
 * ByteOrder#BIG_ENDIAN BIG_ENDIAN}.
 *
 * <p> For access to heterogeneous binary data, that is, sequences of values of
 * different types, this class defines a family of absolute and relative
 * <i>get</i> and <i>put</i> methods for each type.  For 32-bit floating-point
 * values, for example, this class defines:
 *
 * {@snippet lang=java :
 *     // @link substring="getFloat()" target="#getFloat" :
 *     float      getFloat()
 *     // @link substring="getFloat(int index)" target="#getFloat(int)" :
 *     float      getFloat(int index)
 *     // @link substring="putFloat(float f)" target="#putFloat(float)" :
 *     ByteBuffer putFloat(float f)
 *     // @link substring="putFloat(int index, float f)" target="#putFloat(int,float)" :
 *     ByteBuffer putFloat(int index, float f)
 * }
 *
 * <p> Corresponding methods are defined for the types {@code char,
 * short, int, long}, and {@code double}.  The index
 * parameters of the absolute <i>get</i> and <i>put</i> methods are in terms of
 * bytes rather than of the type being read or written.
 *
 * <a id="views"></a>
 *
 * <p> For access to homogeneous binary data, that is, sequences of values of
 * the same type, this class defines methods that can create <i>views</i> of a
 * given byte buffer.  A <i>view buffer</i> is simply another buffer whose
 * content is backed by the byte buffer.  Changes to the byte buffer's content
 * will be visible in the view buffer, and vice versa; the two buffers'
 * position, limit, and mark values are independent.  The {@link
 * #asFloatBuffer() asFloatBuffer} method, for example, creates an instance of
 * the {@link FloatBuffer} class that is backed by the byte buffer upon which
 * the method is invoked.  Corresponding view-creation methods are defined for
 * the types {@code char, short, int, long}, and {@code double}.
 *
 * <p> View buffers have three important advantages over the families of
 * type-specific <i>get</i> and <i>put</i> methods described above:
 *
 * <ul>
 *
 *   <li><p> A view buffer is indexed not in terms of bytes but rather in terms
 *   of the type-specific size of its values;  </p></li>
 *
 *   <li><p> A view buffer provides relative bulk <i>get</i> and <i>put</i>
 *   methods that can transfer contiguous sequences of values between a buffer
 *   and an array or some other buffer of the same type; and  </p></li>
 *
 *   <li><p> A view buffer is potentially much more efficient because it will
 *   be direct if, and only if, its backing byte buffer is direct.  </p></li>
 *
 * </ul>
 *
 * <p> The byte order of a view buffer is fixed to be that of its byte buffer
 * at the time that the view is created.  </p>
 *

*











*










 *

 * <h2> Invocation chaining </h2>

 *
 * <p> Methods in this class that do not otherwise have a value to return are
 * specified to return the buffer upon which they are invoked.  This allows
 * method invocations to be chained.
 *

 *
 * The sequence of statements
 *
 * {@snippet lang=java :
 *     bb.putInt(0xCAFEBABE);
 *     bb.putShort(3);
 *     bb.putShort(45);
 * }
 *
 * can, for example, be replaced by the single statement
 *
 * {@snippet lang=java :
 *     bb.putInt(0xCAFEBABE).putShort(3).putShort(45);
 * }
 *



















 * <h2> Optional operations </h2>
 * Methods specified as
 * <i>{@linkplain Buffer##read-only-buffers-heading optional
 * operations}</i> throw a {@linkplain ReadOnlyBufferException} when invoked
 * on a {@linkplain java.nio.Buffer#isReadOnly read-only} ByteBuffer. The
 * methods {@linkplain #array array} and {@linkplain #arrayOffset arrayOffset}
 * throw an {@linkplain UnsupportedOperationException} if the ByteBuffer is
 * not backed by an {@linkplain Buffer#hasArray accessible byte array}
 * (irrespective of whether the ByteBuffer is read-only).
 *
 *
 * @author Mark Reinhold
 * @author JSR-51 Expert Group
 * @since 1.4
 */

public abstract sealed class ByteBuffer
    extends Buffer
    implements Comparable<ByteBuffer>
    permits

    HeapByteBuffer, MappedByteBuffer







{
    // Cached array base offset
    private static final long ARRAY_BASE_OFFSET = UNSAFE.arrayBaseOffset(byte[].class);

    // These fields are declared here rather than in Heap-X-Buffer in order to
    // reduce the number of virtual method invocations needed to access these
    // values, which is especially costly when coding small buffers.
    //
    final byte[] hb;                  // Non-null only for heap buffers
    final int offset;
    boolean isReadOnly;

    // Creates a new buffer with the given mark, position, limit, capacity,
    // backing array, and array offset
    //
    ByteBuffer(int mark, int pos, int lim, int cap,   // package-private
                 byte[] hb, int offset, MemorySegment segment)
    {
        super(mark, pos, lim, cap, segment);
        this.hb = hb;
        this.offset = offset;
    }

    // Creates a new buffer with the given mark, position, limit, and capacity
    //
    ByteBuffer(int mark, int pos, int lim, int cap, MemorySegment segment) { // package-private
        this(mark, pos, lim, cap, null, 0, segment);
    }

    // Creates a new buffer with given base, address and capacity
    //
    ByteBuffer(byte[] hb, long addr, int cap, MemorySegment segment) { // package-private
        super(addr, cap, segment);
        this.hb = hb;
        this.offset = 0;
    }

    @Override
    Object base() {
        return hb;
    }



    /**
     * Allocates a new direct byte buffer.
     *
     * <p> The new buffer's position will be zero, its limit will be its
     * capacity, its mark will be undefined, each of its elements will be
     * initialized to zero, and its byte order will be
     * {@link ByteOrder#BIG_ENDIAN BIG_ENDIAN}.  Whether or not it has a
     * {@link #hasArray backing array} is unspecified.
     *
     * @param  capacity
     *         The new buffer's capacity, in bytes
     *
     * @return  The new byte buffer
     *
     * @throws  IllegalArgumentException
     *          If the {@code capacity} is a negative integer
     */
    public static ByteBuffer allocateDirect(int capacity) {
        return new DirectByteBuffer(capacity);
    }



    /**
     * Allocates a new byte buffer.
     *
     * <p> The new buffer's position will be zero, its limit will be its
     * capacity, its mark will be undefined, each of its elements will be
     * initialized to zero, and its byte order will be

     * {@link ByteOrder#BIG_ENDIAN BIG_ENDIAN}.




     * It will have a {@link #array backing array}, and its
     * {@link #arrayOffset array offset} will be zero.
     *
     * @param  capacity
     *         The new buffer's capacity, in bytes
     *
     * @return  The new byte buffer
     *
     * @throws  IllegalArgumentException
     *          If the {@code capacity} is a negative integer
     */
    public static ByteBuffer allocate(int capacity) {
        if (capacity < 0)
            throw createCapacityException(capacity);
        return new HeapByteBuffer(capacity, capacity, null);
    }

    /**
     * Wraps a byte array into a buffer.
     *
     * <p> The new buffer will be backed by the given byte array;
     * that is, modifications to the buffer will cause the array to be modified
     * and vice versa.  The new buffer's capacity will be
     * {@code array.length}, its position will be {@code offset}, its limit
     * will be {@code offset + length}, its mark will be undefined, and its
     * byte order will be

     * {@link ByteOrder#BIG_ENDIAN BIG_ENDIAN}.




     * Its {@link #array backing array} will be the given array, and
     * its {@link #arrayOffset array offset} will be zero.  </p>
     *
     * @param  array
     *         The array that will back the new buffer
     *
     * @param  offset
     *         The offset of the subarray to be used; must be non-negative and
     *         no larger than {@code array.length}.  The new buffer's position
     *         will be set to this value.
     *
     * @param  length
     *         The length of the subarray to be used;
     *         must be non-negative and no larger than
     *         {@code array.length - offset}.
     *         The new buffer's limit will be set to {@code offset + length}.
     *
     * @return  The new byte buffer
     *
     * @throws  IndexOutOfBoundsException
     *          If the preconditions on the {@code offset} and {@code length}
     *          parameters do not hold
     */
    public static ByteBuffer wrap(byte[] array,
                                    int offset, int length)
    {
        try {
            return new HeapByteBuffer(array, offset, length, null);
        } catch (IllegalArgumentException x) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Wraps a byte array into a buffer.
     *
     * <p> The new buffer will be backed by the given byte array;
     * that is, modifications to the buffer will cause the array to be modified
     * and vice versa.  The new buffer's capacity and limit will be
     * {@code array.length}, its position will be zero, its mark will be
     * undefined, and its byte order will be

     * {@link ByteOrder#BIG_ENDIAN BIG_ENDIAN}.




     * Its {@link #array backing array} will be the given array, and its
     * {@link #arrayOffset array offset} will be zero.  </p>
     *
     * @param  array
     *         The array that will back this buffer
     *
     * @return  The new byte buffer
     */
    public static ByteBuffer wrap(byte[] array) {
        return wrap(array, 0, array.length);
    }





































































































    /**
     * Creates a new byte buffer whose content is a shared subsequence of
     * this buffer's content.
     *
     * <p> The content of the new buffer will start at this buffer's current
     * position.  Changes to this buffer's content will be visible in the new
     * buffer, and vice versa; the two buffers' position, limit, and mark
     * values will be independent.
     *
     * <p> The new buffer's position will be zero, its capacity and its limit
     * will be the number of bytes remaining in this buffer, its mark will be
     * undefined, and its byte order will be

     * {@link ByteOrder#BIG_ENDIAN BIG_ENDIAN}.



     * The new buffer will be direct if, and only if, this buffer is direct, and
     * it will be read-only if, and only if, this buffer is read-only.  </p>
     *
     * @return  The new byte buffer

     *
     * @see #alignedSlice(int)

     */
    @Override
    public abstract ByteBuffer slice();

    /**
     * Creates a new byte buffer whose content is a shared subsequence of
     * this buffer's content.
     *
     * <p> The content of the new buffer will start at position {@code index}
     * in this buffer, and will contain {@code length} elements. Changes to
     * this buffer's content will be visible in the new buffer, and vice versa;
     * the two buffers' position, limit, and mark values will be independent.
     *
     * <p> The new buffer's position will be zero, its capacity and its limit
     * will be {@code length}, its mark will be undefined, and its byte order
     * will be

     * {@link ByteOrder#BIG_ENDIAN BIG_ENDIAN}.



     * The new buffer will be direct if, and only if, this buffer is direct,
     * and it will be read-only if, and only if, this buffer is read-only. </p>
     *
     * @param   index
     *          The position in this buffer at which the content of the new
     *          buffer will start; must be non-negative and no larger than
     *          {@link #limit() limit()}
     *
     * @param   length
     *          The number of elements the new buffer will contain; must be
     *          non-negative and no larger than {@code limit() - index}
     *
     * @return  The new buffer
     *
     * @throws  IndexOutOfBoundsException
     *          If {@code index} is negative or greater than {@code limit()},
     *          {@code length} is negative, or {@code length > limit() - index}
     *
     * @since 13
     */
    @Override
    public abstract ByteBuffer slice(int index, int length);

    /**
     * Creates a new byte buffer that shares this buffer's content.
     *
     * <p> The content of the new buffer will be that of this buffer.  Changes
     * to this buffer's content will be visible in the new buffer, and vice
     * versa; the two buffers' position, limit, and mark values will be
     * independent.
     *
     * <p> The new buffer's capacity, limit, position,

     * and mark values will be identical to those of this buffer, and its byte
     * order will be {@link ByteOrder#BIG_ENDIAN BIG_ENDIAN}.



     * The new buffer will be direct if, and only if, this buffer is direct, and
     * it will be read-only if, and only if, this buffer is read-only.  </p>
     *
     * @return  The new byte buffer
     */
    @Override
    public abstract ByteBuffer duplicate();

    /**
     * Creates a new, read-only byte buffer that shares this buffer's
     * content.
     *
     * <p> The content of the new buffer will be that of this buffer.  Changes
     * to this buffer's content will be visible in the new buffer; the new
     * buffer itself, however, will be read-only and will not allow the shared
     * content to be modified.  The two buffers' position, limit, and mark
     * values will be independent.
     *
     * <p> The new buffer's capacity, limit, position,

     * and mark values will be identical to those of this buffer, and its byte
     * order will be {@link ByteOrder#BIG_ENDIAN BIG_ENDIAN}.



     *
     * <p> If this buffer is itself read-only then this method behaves in
     * exactly the same way as the {@link #duplicate duplicate} method.  </p>
     *
     * @return  The new, read-only byte buffer
     */
    public abstract ByteBuffer asReadOnlyBuffer();


    // -- Singleton get/put methods --

    /**
     * Relative <i>get</i> method.  Reads the byte at this buffer's
     * current position, and then increments the position.
     *
     * @return  The byte at the buffer's current position
     *
     * @throws  BufferUnderflowException
     *          If the buffer's current position is not smaller than its limit
     */
    public abstract byte get();

    /**
     * Relative <i>put</i> method&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> Writes the given byte into this buffer at the current
     * position, and then increments the position. </p>
     *
     * @param  b
     *         The byte to be written
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     *          If this buffer's current position is not smaller than its limit
     *
     * @throws  ReadOnlyBufferException
     *          If this buffer is read-only
     */
    public abstract ByteBuffer put(byte b);

    /**
     * Absolute <i>get</i> method.  Reads the byte at the given
     * index.
     *
     * @param  index
     *         The index from which the byte will be read
     *
     * @return  The byte at the given index
     *
     * @throws  IndexOutOfBoundsException
     *          If {@code index} is negative
     *          or not smaller than the buffer's limit
     */
    public abstract byte get(int index);














    /**
     * Absolute <i>put</i> method&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> Writes the given byte into this buffer at the given
     * index. </p>
     *
     * @param  index
     *         The index at which the byte will be written
     *
     * @param  b
     *         The byte value to be written
     *
     * @return  This buffer
     *
     * @throws  IndexOutOfBoundsException
     *          If {@code index} is negative
     *          or not smaller than the buffer's limit
     *
     * @throws  ReadOnlyBufferException
     *          If this buffer is read-only
     */
    public abstract ByteBuffer put(int index, byte b);


    // -- Bulk get operations --

    /**
     * Relative bulk <i>get</i> method.
     *
     * <p> This method transfers bytes from this buffer into the given
     * destination array.  If there are fewer bytes remaining in the
     * buffer than are required to satisfy the request, that is, if
     * {@code length}&nbsp;{@code >}&nbsp;{@code remaining()}, then no
     * bytes are transferred and a {@link BufferUnderflowException} is
     * thrown.
     *
     * <p> Otherwise, this method copies {@code length} bytes from this
     * buffer into the given array, starting at the current position of this
     * buffer and at the given offset in the array.  The position of this
     * buffer is then incremented by {@code length}.
     *
     * <p> In other words, an invocation of this method of the form
     * <code>src.get(dst,&nbsp;off,&nbsp;len)</code> has exactly the same effect as
     * the loop
     *
     * {@snippet lang=java :
     *     for (int i = off; i < off + len; i++)
     *         dst[i] = src.get();
     * }
     *
     * except that it first checks that there are sufficient bytes in
     * this buffer and it is potentially much more efficient.
     *
     * @param  dst
     *         The array into which bytes are to be written
     *
     * @param  offset
     *         The offset within the array of the first byte to be
     *         written; must be non-negative and no larger than
     *         {@code dst.length}
     *
     * @param  length
     *         The maximum number of bytes to be written to the given
     *         array; must be non-negative and no larger than
     *         {@code dst.length - offset}
     *
     * @return  This buffer
     *
     * @throws  BufferUnderflowException
     *          If there are fewer than {@code length} bytes
     *          remaining in this buffer
     *
     * @throws  IndexOutOfBoundsException
     *          If the preconditions on the {@code offset} and {@code length}
     *          parameters do not hold
     */
    public ByteBuffer get(byte[] dst, int offset, int length) {
        Objects.checkFromIndexSize(offset, length, dst.length);
        int pos = position();
        if (length > limit() - pos)
            throw new BufferUnderflowException();

        getArray(pos, dst, offset, length);

        position(pos + length);
        return this;
    }

    /**
     * Relative bulk <i>get</i> method.
     *
     * <p> This method transfers bytes from this buffer into the given
     * destination array.  An invocation of this method of the form
     * {@code src.get(a)} behaves in exactly the same way as the invocation
     *
     * {@snippet lang=java :
     *     src.get(a, 0, a.length)
     * }
     *
     * @param   dst
     *          The destination array
     *
     * @return  This buffer
     *
     * @throws  BufferUnderflowException
     *          If there are fewer than {@code length} bytes
     *          remaining in this buffer
     */
    public ByteBuffer get(byte[] dst) {
        return get(dst, 0, dst.length);
    }

    /**
     * Absolute bulk <i>get</i> method.
     *
     * <p> This method transfers {@code length} bytes from this
     * buffer into the given array, starting at the given index in this
     * buffer and at the given offset in the array.  The position of this
     * buffer is unchanged.
     *
     * <p> An invocation of this method of the form
     * <code>src.get(index,&nbsp;dst,&nbsp;offset,&nbsp;length)</code>
     * has exactly the same effect as the following loop except that it first
     * checks the consistency of the supplied parameters and it is potentially
     * much more efficient:
     *
     * {@snippet lang=java :
     *     for (int i = offset, j = index; i < offset + length; i++, j++)
     *         dst[i] = src.get(j);
     * }
     *
     * @param  index
     *         The index in this buffer from which the first byte will be
     *         read; must be non-negative and less than {@code limit()}
     *
     * @param  dst
     *         The destination array
     *
     * @param  offset
     *         The offset within the array of the first byte to be
     *         written; must be non-negative and less than
     *         {@code dst.length}
     *
     * @param  length
     *         The number of bytes to be written to the given array;
     *         must be non-negative and no larger than the smaller of
     *         {@code limit() - index} and {@code dst.length - offset}
     *
     * @return  This buffer
     *
     * @throws  IndexOutOfBoundsException
     *          If the preconditions on the {@code index}, {@code offset}, and
     *          {@code length} parameters do not hold
     *
     * @since 13
     */
    public ByteBuffer get(int index, byte[] dst, int offset, int length) {
        Objects.checkFromIndexSize(index, length, limit());
        Objects.checkFromIndexSize(offset, length, dst.length);

        getArray(index, dst, offset, length);

        return this;
    }

    /**
     * Absolute bulk <i>get</i> method.
     *
     * <p> This method transfers bytes from this buffer into the given
     * destination array.  The position of this buffer is unchanged.  An
     * invocation of this method of the form
     * <code>src.get(index,&nbsp;dst)</code> behaves in exactly the same
     * way as the invocation:
     *
     * {@snippet lang=java :
     *     src.get(index, dst, 0, dst.length)
     * }
     *
     * @param  index
     *         The index in this buffer from which the first byte will be
     *         read; must be non-negative and less than {@code limit()}
     *
     * @param  dst
     *         The destination array
     *
     * @return  This buffer
     *
     * @throws  IndexOutOfBoundsException
     *          If {@code index} is negative, not smaller than {@code limit()},
     *          or {@code limit() - index < dst.length}
     *
     * @since 13
     */
    public ByteBuffer get(int index, byte[] dst) {
        return get(index, dst, 0, dst.length);
    }

    private ByteBuffer getArray(int index, byte[] dst, int offset, int length) {
        if (



            ((long)length << 0) > Bits.JNI_COPY_TO_ARRAY_THRESHOLD) {
            long bufAddr = address + ((long)index << 0);
            long dstOffset =
                ARRAY_BASE_OFFSET + ((long)offset << 0);
            long len = (long)length << 0;

            try {







                    SCOPED_MEMORY_ACCESS.copyMemory(
                            session(), null, base(), bufAddr,
                            dst, dstOffset, len);
            } finally {
                Reference.reachabilityFence(this);
            }
        } else {
            int end = offset + length;
            for (int i = offset, j = index; i < end; i++, j++) {
                dst[i] = get(j);
            }
        }
        return this;
    }

    // -- Bulk put operations --

    /**
     * Relative bulk <i>put</i> method&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> This method transfers the bytes remaining in the given source
     * buffer into this buffer.  If there are more bytes remaining in the
     * source buffer than in this buffer, that is, if
     * {@code src.remaining()}&nbsp;{@code >}&nbsp;{@code remaining()},
     * then no bytes are transferred and a {@link
     * BufferOverflowException} is thrown.
     *
     * <p> Otherwise, this method copies
     * <i>n</i>&nbsp;=&nbsp;{@code src.remaining()} bytes from the given
     * buffer into this buffer, starting at each buffer's current position.
     * The positions of both buffers are then incremented by <i>n</i>.
     *
     * <p> In other words, an invocation of this method of the form
     * {@code dst.put(src)} has exactly the same effect as the loop
     *
     * {@snippet lang=java :
     *     while (src.hasRemaining())
     *         dst.put(src.get());
     * }
     *
     * except that it first checks that there is sufficient space in this
     * buffer and it is potentially much more efficient.  If this buffer and
     * the source buffer share the same backing array or memory, then the
     * result will be as if the source elements were first copied to an
     * intermediate location before being written into this buffer.
     *
     * @param  src
     *         The source buffer from which bytes are to be read;
     *         must not be this buffer
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     *          If there is insufficient space in this buffer
     *          for the remaining bytes in the source buffer
     *
     * @throws  IllegalArgumentException
     *          If the source buffer is this buffer
     *
     * @throws  ReadOnlyBufferException
     *          If this buffer is read-only
     */
    public ByteBuffer put(ByteBuffer src) {
        if (src == this)
            throw createSameBufferException();
        if (isReadOnly())
            throw new ReadOnlyBufferException();

        int srcPos = src.position();
        int srcLim = src.limit();
        int srcRem = (srcPos <= srcLim ? srcLim - srcPos : 0);
        int pos = position();
        int lim = limit();
        int rem = (pos <= lim ? lim - pos : 0);

        if (srcRem > rem)
            throw new BufferOverflowException();

        putBuffer(pos, src, srcPos, srcRem);

        position(pos + srcRem);
        src.position(srcPos + srcRem);

        return this;
    }

    /**
     * Absolute bulk <i>put</i> method&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> This method transfers {@code length} bytes into this buffer from
     * the given source buffer, starting at the given {@code offset} in the
     * source buffer and the given {@code index} in this buffer. The positions
     * of both buffers are unchanged.
     *
     * <p> In other words, an invocation of this method of the form
     * <code>dst.put(index,&nbsp;src,&nbsp;offset,&nbsp;length)</code>
     * has exactly the same effect as the loop
     *
     * {@snippet lang=java :
     *     for (int i = offset, j = index; i < offset + length; i++, j++)
     *         dst.put(j, src.get(i));
     * }
     *
     * except that it first checks the consistency of the supplied parameters
     * and it is potentially much more efficient.  If this buffer and
     * the source buffer share the same backing array or memory, then the
     * result will be as if the source elements were first copied to an
     * intermediate location before being written into this buffer.
     *
     * @param index
     *        The index in this buffer at which the first byte will be
     *        written; must be non-negative and less than {@code limit()}
     *
     * @param src
     *        The buffer from which bytes are to be read
     *
     * @param offset
     *        The index within the source buffer of the first byte to be
     *        read; must be non-negative and less than {@code src.limit()}
     *
     * @param length
     *        The number of bytes to be read from the given buffer;
     *        must be non-negative and no larger than the smaller of
     *        {@code limit() - index} and {@code src.limit() - offset}
     *
     * @return This buffer
     *
     * @throws IndexOutOfBoundsException
     *         If the preconditions on the {@code index}, {@code offset}, and
     *         {@code length} parameters do not hold
     *
     * @throws ReadOnlyBufferException
     *         If this buffer is read-only
     *
     * @since 16
     */
    public ByteBuffer put(int index, ByteBuffer src, int offset, int length) {
        Objects.checkFromIndexSize(index, length, limit());
        Objects.checkFromIndexSize(offset, length, src.limit());
        if (isReadOnly())
            throw new ReadOnlyBufferException();

        putBuffer(index, src, offset, length);

        return this;
    }

    void putBuffer(int pos, ByteBuffer src, int srcPos, int n) {

        Object srcBase = src.base();



        assert srcBase != null || src.isDirect();


            Object base = base();
            assert base != null || isDirect();

            long srcAddr = src.address + ((long)srcPos << 0);
            long addr = address + ((long)pos << 0);
            long len = (long)n << 0;

            try {







                    SCOPED_MEMORY_ACCESS.copyMemory(
                            src.session(), session(), srcBase, srcAddr,
                            base, addr, len);
            } finally {
                Reference.reachabilityFence(src);
                Reference.reachabilityFence(this);
            }











    }

    /**
     * Relative bulk <i>put</i> method&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> This method transfers bytes into this buffer from the given
     * source array.  If there are more bytes to be copied from the array
     * than remain in this buffer, that is, if
     * {@code length}&nbsp;{@code >}&nbsp;{@code remaining()}, then no
     * bytes are transferred and a {@link BufferOverflowException} is
     * thrown.
     *
     * <p> Otherwise, this method copies {@code length} bytes from the
     * given array into this buffer, starting at the given offset in the array
     * and at the current position of this buffer.  The position of this buffer
     * is then incremented by {@code length}.
     *
     * <p> In other words, an invocation of this method of the form
     * <code>dst.put(src,&nbsp;off,&nbsp;len)</code> has exactly the same effect as
     * the loop
     *
     * {@snippet lang=java :
     *     for (int i = off; i < off + len; i++)
     *         dst.put(src[i]);
     * }
     *
     * except that it first checks that there is sufficient space in this
     * buffer and it is potentially much more efficient.
     *
     * @param  src
     *         The array from which bytes are to be read
     *
     * @param  offset
     *         The offset within the array of the first byte to be read;
     *         must be non-negative and no larger than {@code src.length}
     *
     * @param  length
     *         The number of bytes to be read from the given array;
     *         must be non-negative and no larger than
     *         {@code src.length - offset}
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     *          If there is insufficient space in this buffer
     *
     * @throws  IndexOutOfBoundsException
     *          If the preconditions on the {@code offset} and {@code length}
     *          parameters do not hold
     *
     * @throws  ReadOnlyBufferException
     *          If this buffer is read-only
     */
    public ByteBuffer put(byte[] src, int offset, int length) {
        if (isReadOnly())
            throw new ReadOnlyBufferException();
        Objects.checkFromIndexSize(offset, length, src.length);
        int pos = position();
        if (length > limit() - pos)
            throw new BufferOverflowException();

        putArray(pos, src, offset, length);

        position(pos + length);
        return this;
    }

    /**
     * Relative bulk <i>put</i> method&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> This method transfers the entire content of the given source
     * byte array into this buffer.  An invocation of this method of the
     * form {@code dst.put(a)} behaves in exactly the same way as the
     * invocation
     *
     * {@snippet lang=java :
     *     dst.put(a, 0, a.length)
     * }
     *
     * @param   src
     *          The source array
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     *          If there is insufficient space in this buffer
     *
     * @throws  ReadOnlyBufferException
     *          If this buffer is read-only
     */
    public final ByteBuffer put(byte[] src) {
        return put(src, 0, src.length);
    }

    /**
     * Absolute bulk <i>put</i> method&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> This method transfers {@code length} bytes from the given
     * array, starting at the given offset in the array and at the given index
     * in this buffer.  The position of this buffer is unchanged.
     *
     * <p> An invocation of this method of the form
     * <code>dst.put(index,&nbsp;src,&nbsp;offset,&nbsp;length)</code>
     * has exactly the same effect as the following loop except that it first
     * checks the consistency of the supplied parameters and it is potentially
     * much more efficient:
     *
     * {@snippet lang=java :
     *     for (int i = offset, j = index; i < offset + length; i++, j++)
     *         dst.put(j, src[i]);
     * }
     *
     * @param  index
     *         The index in this buffer at which the first byte will be
     *         written; must be non-negative and less than {@code limit()}
     *
     * @param  src
     *         The array from which bytes are to be read
     *
     * @param  offset
     *         The offset within the array of the first byte to be read;
     *         must be non-negative and less than {@code src.length}
     *
     * @param  length
     *         The number of bytes to be read from the given array;
     *         must be non-negative and no larger than the smaller of
     *         {@code limit() - index} and {@code src.length - offset}
     *
     * @return  This buffer
     *
     * @throws  IndexOutOfBoundsException
     *          If the preconditions on the {@code index}, {@code offset}, and
     *          {@code length} parameters do not hold
     *
     * @throws  ReadOnlyBufferException
     *          If this buffer is read-only
     *
     * @since 13
     */
    public ByteBuffer put(int index, byte[] src, int offset, int length) {
        if (isReadOnly())
            throw new ReadOnlyBufferException();
        Objects.checkFromIndexSize(index, length, limit());
        Objects.checkFromIndexSize(offset, length, src.length);

        putArray(index, src, offset, length);

        return this;
    }

    /**
     * Absolute bulk <i>put</i> method&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> This method copies bytes into this buffer from the given source
     * array.  The position of this buffer is unchanged.  An invocation of this
     * method of the form <code>dst.put(index,&nbsp;src)</code>
     * behaves in exactly the same way as the invocation:
     *
     * {@snippet lang=java :
     *     dst.put(index, src, 0, src.length);
     * }
     *
     * @param  index
     *         The index in this buffer at which the first byte will be
     *         written; must be non-negative and less than {@code limit()}
     *
     * @param  src
     *         The array from which bytes are to be read
     *
     * @return  This buffer
     *
     * @throws  IndexOutOfBoundsException
     *          If {@code index} is negative, not smaller than {@code limit()},
     *          or {@code limit() - index < src.length}
     *
     * @throws  ReadOnlyBufferException
     *          If this buffer is read-only
     *
     * @since 13
     */
    public ByteBuffer put(int index, byte[] src) {
        return put(index, src, 0, src.length);
    }

    ByteBuffer putArray(int index, byte[] src, int offset, int length) {

        if (



            ((long)length << 0) > Bits.JNI_COPY_FROM_ARRAY_THRESHOLD) {
            long bufAddr = address + ((long)index << 0);
            long srcOffset =
                ARRAY_BASE_OFFSET + ((long)offset << 0);
            long len = (long)length << 0;

            try {







                    SCOPED_MEMORY_ACCESS.copyMemory(
                            null, session(), src, srcOffset,
                            base(), bufAddr, len);
            } finally {
                Reference.reachabilityFence(this);
            }
        } else {
            int end = offset + length;
            for (int i = offset, j = index; i < end; i++, j++)
                this.put(j, src[i]);
        }
        return this;



    }
































































































    // -- Other stuff --

    /**
     * Tells whether or not this buffer is backed by an accessible byte
     * array.
     *
     * <p> If this method returns {@code true} then the {@link #array() array}
     * and {@link #arrayOffset() arrayOffset} methods may safely be invoked.
     * </p>
     *
     * @return  {@code true} if, and only if, this buffer
     *          is backed by an array and is not read-only
     */
    public final boolean hasArray() {
        return (hb != null) && !isReadOnly;
    }

    /**
     * Returns the byte array that backs this
     * buffer&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> Modifications to this buffer's content will cause the returned
     * array's content to be modified, and vice versa.
     *
     * <p> Invoke the {@link #hasArray hasArray} method before invoking this
     * method in order to ensure that this buffer has an accessible backing
     * array.  </p>
     *
     * @return  The array that backs this buffer
     *
     * @throws  ReadOnlyBufferException
     *          If this buffer is backed by an array but is read-only
     *
     * @throws  UnsupportedOperationException
     *          If this buffer is not backed by an accessible array
     */
    public final byte[] array() {
        if (hb == null)
            throw new UnsupportedOperationException();
        if (isReadOnly)
            throw new ReadOnlyBufferException();
        return hb;
    }

    /**
     * Returns the offset within this buffer's backing array of the first
     * element of the buffer&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> If this buffer is backed by an array then buffer position <i>p</i>
     * corresponds to array index <i>p</i>&nbsp;+&nbsp;{@code arrayOffset()}.
     *
     * <p> Invoke the {@link #hasArray hasArray} method before invoking this
     * method in order to ensure that this buffer has an accessible backing
     * array.  </p>
     *
     * @return  The offset within this buffer's array
     *          of the first element of the buffer
     *
     * @throws  ReadOnlyBufferException
     *          If this buffer is backed by an array but is read-only
     *
     * @throws  UnsupportedOperationException
     *          If this buffer is not backed by an accessible array
     */
    public final int arrayOffset() {
        if (hb == null)
            throw new UnsupportedOperationException();
        if (isReadOnly)
            throw new ReadOnlyBufferException();
        return offset;
    }

    // -- Covariant return type overrides

    /**
     * {@inheritDoc}
     * @since 9
     */
    @Override
    public



    ByteBuffer position(int newPosition) {
        super.position(newPosition);
        return this;
    }
    
    /**
     * {@inheritDoc}
     * @since 9
     */
    @Override
    public



    ByteBuffer limit(int newLimit) {
        super.limit(newLimit);
        return this;
    }
    
    /**
     * {@inheritDoc}
     * @since 9
     */
    @Override
    public 



    ByteBuffer mark() {
        super.mark();
        return this;
    }

    /**
     * {@inheritDoc}
     * @since 9
     */
    @Override
    public 



    ByteBuffer reset() {
        super.reset();
        return this;
    }

    /**
     * {@inheritDoc}
     * @since 9
     */
    @Override
    public 



    ByteBuffer clear() {
        super.clear();
        return this;
    }

    /**
     * {@inheritDoc}
     * @since 9
     */
    @Override
    public 



    ByteBuffer flip() {
        super.flip();
        return this;
    }

    /**
     * {@inheritDoc}
     * @since 9
     */
    @Override
    public 



    ByteBuffer rewind() {
        super.rewind();
        return this;
    }

    /**
     * Compacts this buffer&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> The bytes between the buffer's current position and its limit,
     * if any, are copied to the beginning of the buffer.  That is, the
     * byte at index <i>p</i>&nbsp;=&nbsp;{@code position()} is copied
     * to index zero, the byte at index <i>p</i>&nbsp;+&nbsp;1 is copied
     * to index one, and so forth until the byte at index
     * {@code limit()}&nbsp;-&nbsp;1 is copied to index
     * <i>n</i>&nbsp;=&nbsp;{@code limit()}&nbsp;-&nbsp;{@code 1}&nbsp;-&nbsp;<i>p</i>.
     * The buffer's position is then set to <i>n+1</i> and its limit is set to
     * its capacity.  The mark, if defined, is discarded.
     *
     * <p> The buffer's position is set to the number of bytes copied,
     * rather than to zero, so that an invocation of this method can be
     * followed immediately by an invocation of another relative <i>put</i>
     * method. </p>
     *

     *
     * <p> Invoke this method after writing data from a buffer in case the
     * write was incomplete.  The following loop, for example, copies bytes
     * from one channel to another via the buffer {@code buf}:
     *
     * {@snippet lang=java :
     *     buf.clear();          // Prepare buffer for use
     *     while (in.read(buf) >= 0 || buf.position != 0) {
     *         buf.flip();
     *         out.write(buf);
     *         buf.compact();    // In case of partial write
     *     }
     * }
     *

     *
     * @return  This buffer
     *
     * @throws  ReadOnlyBufferException
     *          If this buffer is read-only
     */
    public abstract ByteBuffer compact();

    /**
     * Tells whether or not this byte buffer is direct.
     *
     * @return  {@code true} if, and only if, this buffer is direct
     */
    public abstract boolean isDirect();

















    /**
     * Returns a string summarizing the state of this buffer.
     *
     * @return  A summary string
     */
    public String toString() {
        return getClass().getName()
                 + "[pos=" + position()
                 + " lim=" + limit()
                 + " cap=" + capacity()
                 + "]";
    }






    /**
     * Returns the current hash code of this buffer.
     *
     * <p> The hash code of a byte buffer depends only upon its remaining
     * elements; that is, upon the elements from {@code position()} up to, and
     * including, the element at {@code limit()}&nbsp;-&nbsp;{@code 1}.
     *
     * <p> Because buffer hash codes are content-dependent, it is inadvisable
     * to use buffers as keys in hash maps or similar data structures unless it
     * is known that their contents will not change.  </p>
     *
     * @return  The current hash code of this buffer
     */
    public int hashCode() {
        int h = 1;
        int p = position();
        for (int i = limit() - 1; i >= p; i--)



            h = 31 * h + (int)get(i);

        return h;
    }

    /**
     * Tells whether or not this buffer is equal to another object.
     *
     * <p> Two byte buffers are equal if, and only if,
     *
     * <ol>
     *
     *   <li><p> They have the same element type,  </p></li>
     *
     *   <li><p> They have the same number of remaining elements, and
     *   </p></li>
     *
     *   <li><p> The two sequences of remaining elements, considered
     *   independently of their starting positions, are pointwise equal.







     *   </p></li>
     *
     * </ol>
     *
     * <p> A byte buffer is not equal to any other type of object.  </p>
     *
     * @param  ob  The object to which this buffer is to be compared
     *
     * @return  {@code true} if, and only if, this buffer is equal to the
     *           given object
     */
    public boolean equals(Object ob) {
        if (this == ob)
            return true;
        if (!(ob instanceof ByteBuffer))
            return false;
        ByteBuffer that = (ByteBuffer)ob;
        int thisPos = this.position();
        int thisRem = this.limit() - thisPos;
        int thatPos = that.position();
        int thatRem = that.limit() - thatPos;
        if (thisRem < 0 || thisRem != thatRem)
            return false;
        return BufferMismatch.mismatch(this, thisPos,
                                       that, thatPos,
                                       thisRem) < 0;
    }

    /**
     * Compares this buffer to another.
     *
     * <p> Two byte buffers are compared by comparing their sequences of
     * remaining elements lexicographically, without regard to the starting
     * position of each sequence within its corresponding buffer.








     * Pairs of {@code byte} elements are compared as if by invoking
     * {@link Byte#compare(byte,byte)}.

     *
     * <p> A byte buffer is not comparable to any other type of object.
     *
     * @return  A negative integer, zero, or a positive integer as this buffer
     *          is less than, equal to, or greater than the given buffer
     */
    public int compareTo(ByteBuffer that) {
        int thisPos = this.position();
        int thisRem = this.limit() - thisPos;
        int thatPos = that.position();
        int thatRem = that.limit() - thatPos;
        int length = Math.min(thisRem, thatRem);
        if (length < 0)
            return -1;
        int i = BufferMismatch.mismatch(this, thisPos,
                                        that, thatPos,
                                        length);
        if (i >= 0) {
            return compare(this.get(thisPos + i), that.get(thatPos + i));
        }
        return thisRem - thatRem;
    }

    private static int compare(byte x, byte y) {






        return Byte.compare(x, y);

    }

    /**
     * Finds and returns the relative index of the first mismatch between this
     * buffer and a given buffer.  The index is relative to the
     * {@link #position() position} of each buffer and will be in the range of
     * 0 (inclusive) up to the smaller of the {@link #remaining() remaining}
     * elements in each buffer (exclusive).
     *
     * <p> If the two buffers share a common prefix then the returned index is
     * the length of the common prefix and it follows that there is a mismatch
     * between the two buffers at that index within the respective buffers.
     * If one buffer is a proper prefix of the other then the returned index is
     * the smaller of the remaining elements in each buffer, and it follows that
     * the index is only valid for the buffer with the larger number of
     * remaining elements.
     * Otherwise, there is no mismatch.
     *
     * @param  that
     *         The byte buffer to be tested for a mismatch with this buffer
     *
     * @return  The relative index of the first mismatch between this and the
     *          given buffer, otherwise -1 if no mismatch.
     *
     * @since 11
     */
    public int mismatch(ByteBuffer that) {
        int thisPos = this.position();
        int thisRem = this.limit() - thisPos;
        int thatPos = that.position();
        int thatRem = that.limit() - thatPos;
        int length = Math.min(thisRem, thatRem);
        if (length < 0)
            return -1;
        int r = BufferMismatch.mismatch(this, thisPos,
                                        that, thatPos,
                                        length);
        return (r == -1 && thisRem != thatRem) ? length : r;
    }

    // -- Other char stuff --






































































































































































































































    // -- Other byte stuff: Access to binary data --



























    boolean bigEndian                                   // package-private
        = true;
    boolean nativeByteOrder                             // package-private
        = (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);

    /**
     * Retrieves this buffer's byte order.
     *
     * <p> The byte order is used when reading or writing multibyte values, and
     * when creating buffers that are views of this byte buffer.  The order of
     * a newly-created byte buffer is always {@link ByteOrder#BIG_ENDIAN
     * BIG_ENDIAN}.  </p>
     *
     * @return  This buffer's byte order
     */
    public final ByteOrder order() {
        return bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
    }

    /**
     * Modifies this buffer's byte order.
     *
     * @param  bo
     *         The new byte order,
     *         either {@link ByteOrder#BIG_ENDIAN BIG_ENDIAN}
     *         or {@link ByteOrder#LITTLE_ENDIAN LITTLE_ENDIAN}
     *
     * @return  This buffer
     */
    public final ByteBuffer order(ByteOrder bo) {
        bigEndian = (bo == ByteOrder.BIG_ENDIAN);
        nativeByteOrder =
            (bigEndian == (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN));
        return this;
    }

    /**
     * Returns the memory address, pointing to the byte at the given index,
     * modulo the given unit size.
     *
     * <p> The return value is non-negative in the range of {@code 0}
     * (inclusive) up to {@code unitSize} (exclusive), with zero indicating
     * that the address of the byte at the index is aligned for the unit size,
     * and a positive value that the address is misaligned for the unit size.
     * If the address of the byte at the index is misaligned, the return value
     * represents how much the index should be adjusted to locate a byte at an
     * aligned address.  Specifically, the index should either be decremented by
     * the return value if the latter is not greater than {@code index}, or be
     * incremented by the unit size minus the return value.  Therefore given
     * {@snippet lang=java :
     *     int value = alignmentOffset(index, unitSize)
     * }
     * then the identities
     * {@snippet lang=java :
     *     alignmentOffset(index - value, unitSize) == 0, value <= index
     * }
     * and
     * {@snippet lang=java :
     *     alignmentOffset(index + (unitSize - value), unitSize) == 0
     * }
     * must hold.
     * 
     * @apiNote
     * This method may be utilized to determine if unit size bytes from an
     * index can be accessed atomically, if supported by the native platform.
     *
     * @implNote
     * This implementation throws {@code UnsupportedOperationException} for
     * non-direct buffers when the given unit size is greater then {@code 8}.
     *
     * @param  index
     *         The index to query for alignment offset, must be non-negative, no
     *         upper bounds check is performed
     *
     * @param  unitSize
     *         The unit size in bytes, must be a power of {@code 2}
     *
     * @return  The indexed byte's memory address modulo the unit size
     *
     * @throws IllegalArgumentException
     *         If the index is negative or the unit size is not a power of
     *         {@code 2}
     *
     * @throws UnsupportedOperationException
     *         If the native platform does not guarantee stable alignment offset
     *         values for the given unit size when managing the memory regions
     *         of buffers of the same kind as this buffer (direct or
     *         non-direct).  For example, if garbage collection would result
     *         in the moving of a memory region covered by a non-direct buffer
     *         from one location to another and both locations have different
     *         alignment characteristics.
     *
     * @see #alignedSlice(int)
     * @since 9
     */
    public final int alignmentOffset(int index, int unitSize) {
        if (index < 0)
            throw new IllegalArgumentException("Index less than zero: " + index);
        if (unitSize < 1 || (unitSize & (unitSize - 1)) != 0)
            throw new IllegalArgumentException("Unit size not a power of two: " + unitSize);
        if (unitSize > 8 && !isDirect())
            throw new UnsupportedOperationException("Unit size unsupported for non-direct buffers: " + unitSize);

        return (int) ((address + index) & (unitSize - 1));
    }

    /**
     * Creates a new byte buffer whose content is a shared and aligned
     * subsequence of this buffer's content.
     *
     * <p> The content of the new buffer will start at this buffer's current
     * position rounded up to the index of the nearest aligned byte for the
     * given unit size, and end at this buffer's limit rounded down to the index
     * of the nearest aligned byte for the given unit size.
     * If rounding results in out-of-bound values then the new buffer's capacity
     * and limit will be zero.  If rounding is within bounds the following
     * expressions will be true for a new buffer {@code nb} and unit size
     * {@code unitSize}:
     * {@snippet lang=java :
     *     nb.alignmentOffset(0, unitSize) == 0
     *     nb.alignmentOffset(nb.limit(), unitSize) == 0
     * }
     *
     * <p> Changes to this buffer's content will be visible in the new
     * buffer, and vice versa; the two buffers' position, limit, and mark
     * values will be independent.
     *
     * <p> The new buffer's position will be zero, its capacity and its limit
     * will be the number of bytes remaining in this buffer or fewer subject to
     * alignment, its mark will be undefined, and its byte order will be
     * {@link ByteOrder#BIG_ENDIAN BIG_ENDIAN}.
     *
     * The new buffer will be direct if, and only if, this buffer is direct, and
     * it will be read-only if, and only if, this buffer is read-only.  </p>
     *
     * @apiNote
     * This method may be utilized to create a new buffer where unit size bytes
     * from index, that is a multiple of the unit size, may be accessed
     * atomically, if supported by the native platform.
     *
     * @implNote
     * This implementation throws {@code UnsupportedOperationException} for
     * non-direct buffers when the given unit size is greater then {@code 8}.
     *
     * @param  unitSize
     *         The unit size in bytes, must be a power of {@code 2}
     *
     * @return  The new byte buffer
     *
     * @throws IllegalArgumentException
     *         If the unit size not a power of {@code 2}
     *
     * @throws UnsupportedOperationException
     *         If the native platform does not guarantee stable aligned slices
     *         for the given unit size when managing the memory regions
     *         of buffers of the same kind as this buffer (direct or
     *         non-direct).  For example, if garbage collection would result
     *         in the moving of a memory region covered by a non-direct buffer
     *         from one location to another and both locations have different
     *         alignment characteristics.
     *
     * @see #alignmentOffset(int, int)
     * @see #slice()
     * @since 9
     */
    public final ByteBuffer alignedSlice(int unitSize) {
        int pos = position();
        int lim = limit();

        int pos_mod = alignmentOffset(pos, unitSize);
        int lim_mod = alignmentOffset(lim, unitSize);

        // Round up the position to align with unit size
        int aligned_pos = (pos_mod > 0)
            ? pos + (unitSize - pos_mod)
            : pos;

        // Round down the limit to align with unit size
        int aligned_lim = lim - lim_mod;

        if (aligned_pos > lim || aligned_lim < pos) {
            aligned_pos = aligned_lim = pos;
        }

        return slice(aligned_pos, aligned_lim - aligned_pos);
    }


    /**
     * Relative <i>get</i> method for reading a char value.
     *
     * <p> Reads the next two bytes at this buffer's current position,
     * composing them into a char value according to the current byte order,
     * and then increments the position by two.  </p>
     *
     * @return  The char value at the buffer's current position
     *
     * @throws  BufferUnderflowException
     *          If there are fewer than two bytes
     *          remaining in this buffer
     */
    public abstract char getChar();

    /**
     * Relative <i>put</i> method for writing a char
     * value&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> Writes two bytes containing the given char value, in the
     * current byte order, into this buffer at the current position, and then
     * increments the position by two.  </p>
     *
     * @param  value
     *         The char value to be written
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     *          If there are fewer than two bytes
     *          remaining in this buffer
     *
     * @throws  ReadOnlyBufferException
     *          If this buffer is read-only
     */
    public abstract ByteBuffer putChar(char value);

    /**
     * Absolute <i>get</i> method for reading a char value.
     *
     * <p> Reads two bytes at the given index, composing them into a
     * char value according to the current byte order.  </p>
     *
     * @param  index
     *         The index from which the bytes will be read
     *
     * @return  The char value at the given index
     *
     * @throws  IndexOutOfBoundsException
     *          If {@code index} is negative
     *          or not smaller than the buffer's limit,
     *          minus one
     */
    public abstract char getChar(int index);

    /**
     * Absolute <i>put</i> method for writing a char
     * value&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> Writes two bytes containing the given char value, in the
     * current byte order, into this buffer at the given index.  </p>
     *
     * @param  index
     *         The index at which the bytes will be written
     *
     * @param  value
     *         The char value to be written
     *
     * @return  This buffer
     *
     * @throws  IndexOutOfBoundsException
     *          If {@code index} is negative
     *          or not smaller than the buffer's limit,
     *          minus one
     *
     * @throws  ReadOnlyBufferException
     *          If this buffer is read-only
     */
    public abstract ByteBuffer putChar(int index, char value);

    /**
     * Creates a view of this byte buffer as a char buffer.
     *
     * <p> The content of the new buffer will start at this buffer's current
     * position.  Changes to this buffer's content will be visible in the new
     * buffer, and vice versa; the two buffers' position, limit, and mark
     * values will be independent.
     *
     * <p> The new buffer's position will be zero, its capacity and its limit
     * will be the number of bytes remaining in this buffer divided by
     * two, its mark will be undefined, and its byte order will be that
     * of the byte buffer at the moment the view is created.  The new buffer
     * will be direct if, and only if, this buffer is direct, and it will be
     * read-only if, and only if, this buffer is read-only.  </p>
     *
     * @return  A new char buffer
     */
    public abstract CharBuffer asCharBuffer();


    /**
     * Relative <i>get</i> method for reading a short value.
     *
     * <p> Reads the next two bytes at this buffer's current position,
     * composing them into a short value according to the current byte order,
     * and then increments the position by two.  </p>
     *
     * @return  The short value at the buffer's current position
     *
     * @throws  BufferUnderflowException
     *          If there are fewer than two bytes
     *          remaining in this buffer
     */
    public abstract short getShort();

    /**
     * Relative <i>put</i> method for writing a short
     * value&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> Writes two bytes containing the given short value, in the
     * current byte order, into this buffer at the current position, and then
     * increments the position by two.  </p>
     *
     * @param  value
     *         The short value to be written
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     *          If there are fewer than two bytes
     *          remaining in this buffer
     *
     * @throws  ReadOnlyBufferException
     *          If this buffer is read-only
     */
    public abstract ByteBuffer putShort(short value);

    /**
     * Absolute <i>get</i> method for reading a short value.
     *
     * <p> Reads two bytes at the given index, composing them into a
     * short value according to the current byte order.  </p>
     *
     * @param  index
     *         The index from which the bytes will be read
     *
     * @return  The short value at the given index
     *
     * @throws  IndexOutOfBoundsException
     *          If {@code index} is negative
     *          or not smaller than the buffer's limit,
     *          minus one
     */
    public abstract short getShort(int index);

    /**
     * Absolute <i>put</i> method for writing a short
     * value&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> Writes two bytes containing the given short value, in the
     * current byte order, into this buffer at the given index.  </p>
     *
     * @param  index
     *         The index at which the bytes will be written
     *
     * @param  value
     *         The short value to be written
     *
     * @return  This buffer
     *
     * @throws  IndexOutOfBoundsException
     *          If {@code index} is negative
     *          or not smaller than the buffer's limit,
     *          minus one
     *
     * @throws  ReadOnlyBufferException
     *          If this buffer is read-only
     */
    public abstract ByteBuffer putShort(int index, short value);

    /**
     * Creates a view of this byte buffer as a short buffer.
     *
     * <p> The content of the new buffer will start at this buffer's current
     * position.  Changes to this buffer's content will be visible in the new
     * buffer, and vice versa; the two buffers' position, limit, and mark
     * values will be independent.
     *
     * <p> The new buffer's position will be zero, its capacity and its limit
     * will be the number of bytes remaining in this buffer divided by
     * two, its mark will be undefined, and its byte order will be that
     * of the byte buffer at the moment the view is created.  The new buffer
     * will be direct if, and only if, this buffer is direct, and it will be
     * read-only if, and only if, this buffer is read-only.  </p>
     *
     * @return  A new short buffer
     */
    public abstract ShortBuffer asShortBuffer();


    /**
     * Relative <i>get</i> method for reading an int value.
     *
     * <p> Reads the next four bytes at this buffer's current position,
     * composing them into an int value according to the current byte order,
     * and then increments the position by four.  </p>
     *
     * @return  The int value at the buffer's current position
     *
     * @throws  BufferUnderflowException
     *          If there are fewer than four bytes
     *          remaining in this buffer
     */
    public abstract int getInt();

    /**
     * Relative <i>put</i> method for writing an int
     * value&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> Writes four bytes containing the given int value, in the
     * current byte order, into this buffer at the current position, and then
     * increments the position by four.  </p>
     *
     * @param  value
     *         The int value to be written
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     *          If there are fewer than four bytes
     *          remaining in this buffer
     *
     * @throws  ReadOnlyBufferException
     *          If this buffer is read-only
     */
    public abstract ByteBuffer putInt(int value);

    /**
     * Absolute <i>get</i> method for reading an int value.
     *
     * <p> Reads four bytes at the given index, composing them into a
     * int value according to the current byte order.  </p>
     *
     * @param  index
     *         The index from which the bytes will be read
     *
     * @return  The int value at the given index
     *
     * @throws  IndexOutOfBoundsException
     *          If {@code index} is negative
     *          or not smaller than the buffer's limit,
     *          minus three
     */
    public abstract int getInt(int index);

    /**
     * Absolute <i>put</i> method for writing an int
     * value&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> Writes four bytes containing the given int value, in the
     * current byte order, into this buffer at the given index.  </p>
     *
     * @param  index
     *         The index at which the bytes will be written
     *
     * @param  value
     *         The int value to be written
     *
     * @return  This buffer
     *
     * @throws  IndexOutOfBoundsException
     *          If {@code index} is negative
     *          or not smaller than the buffer's limit,
     *          minus three
     *
     * @throws  ReadOnlyBufferException
     *          If this buffer is read-only
     */
    public abstract ByteBuffer putInt(int index, int value);

    /**
     * Creates a view of this byte buffer as an int buffer.
     *
     * <p> The content of the new buffer will start at this buffer's current
     * position.  Changes to this buffer's content will be visible in the new
     * buffer, and vice versa; the two buffers' position, limit, and mark
     * values will be independent.
     *
     * <p> The new buffer's position will be zero, its capacity and its limit
     * will be the number of bytes remaining in this buffer divided by
     * four, its mark will be undefined, and its byte order will be that
     * of the byte buffer at the moment the view is created.  The new buffer
     * will be direct if, and only if, this buffer is direct, and it will be
     * read-only if, and only if, this buffer is read-only.  </p>
     *
     * @return  A new int buffer
     */
    public abstract IntBuffer asIntBuffer();


    /**
     * Relative <i>get</i> method for reading a long value.
     *
     * <p> Reads the next eight bytes at this buffer's current position,
     * composing them into a long value according to the current byte order,
     * and then increments the position by eight.  </p>
     *
     * @return  The long value at the buffer's current position
     *
     * @throws  BufferUnderflowException
     *          If there are fewer than eight bytes
     *          remaining in this buffer
     */
    public abstract long getLong();

    /**
     * Relative <i>put</i> method for writing a long
     * value&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> Writes eight bytes containing the given long value, in the
     * current byte order, into this buffer at the current position, and then
     * increments the position by eight.  </p>
     *
     * @param  value
     *         The long value to be written
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     *          If there are fewer than eight bytes
     *          remaining in this buffer
     *
     * @throws  ReadOnlyBufferException
     *          If this buffer is read-only
     */
    public abstract ByteBuffer putLong(long value);

    /**
     * Absolute <i>get</i> method for reading a long value.
     *
     * <p> Reads eight bytes at the given index, composing them into a
     * long value according to the current byte order.  </p>
     *
     * @param  index
     *         The index from which the bytes will be read
     *
     * @return  The long value at the given index
     *
     * @throws  IndexOutOfBoundsException
     *          If {@code index} is negative
     *          or not smaller than the buffer's limit,
     *          minus seven
     */
    public abstract long getLong(int index);

    /**
     * Absolute <i>put</i> method for writing a long
     * value&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> Writes eight bytes containing the given long value, in the
     * current byte order, into this buffer at the given index.  </p>
     *
     * @param  index
     *         The index at which the bytes will be written
     *
     * @param  value
     *         The long value to be written
     *
     * @return  This buffer
     *
     * @throws  IndexOutOfBoundsException
     *          If {@code index} is negative
     *          or not smaller than the buffer's limit,
     *          minus seven
     *
     * @throws  ReadOnlyBufferException
     *          If this buffer is read-only
     */
    public abstract ByteBuffer putLong(int index, long value);

    /**
     * Creates a view of this byte buffer as a long buffer.
     *
     * <p> The content of the new buffer will start at this buffer's current
     * position.  Changes to this buffer's content will be visible in the new
     * buffer, and vice versa; the two buffers' position, limit, and mark
     * values will be independent.
     *
     * <p> The new buffer's position will be zero, its capacity and its limit
     * will be the number of bytes remaining in this buffer divided by
     * eight, its mark will be undefined, and its byte order will be that
     * of the byte buffer at the moment the view is created.  The new buffer
     * will be direct if, and only if, this buffer is direct, and it will be
     * read-only if, and only if, this buffer is read-only.  </p>
     *
     * @return  A new long buffer
     */
    public abstract LongBuffer asLongBuffer();


    /**
     * Relative <i>get</i> method for reading a float value.
     *
     * <p> Reads the next four bytes at this buffer's current position,
     * composing them into a float value according to the current byte order,
     * and then increments the position by four.  </p>
     *
     * @return  The float value at the buffer's current position
     *
     * @throws  BufferUnderflowException
     *          If there are fewer than four bytes
     *          remaining in this buffer
     */
    public abstract float getFloat();

    /**
     * Relative <i>put</i> method for writing a float
     * value&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> Writes four bytes containing the given float value, in the
     * current byte order, into this buffer at the current position, and then
     * increments the position by four.  </p>
     *
     * @param  value
     *         The float value to be written
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     *          If there are fewer than four bytes
     *          remaining in this buffer
     *
     * @throws  ReadOnlyBufferException
     *          If this buffer is read-only
     */
    public abstract ByteBuffer putFloat(float value);

    /**
     * Absolute <i>get</i> method for reading a float value.
     *
     * <p> Reads four bytes at the given index, composing them into a
     * float value according to the current byte order.  </p>
     *
     * @param  index
     *         The index from which the bytes will be read
     *
     * @return  The float value at the given index
     *
     * @throws  IndexOutOfBoundsException
     *          If {@code index} is negative
     *          or not smaller than the buffer's limit,
     *          minus three
     */
    public abstract float getFloat(int index);

    /**
     * Absolute <i>put</i> method for writing a float
     * value&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> Writes four bytes containing the given float value, in the
     * current byte order, into this buffer at the given index.  </p>
     *
     * @param  index
     *         The index at which the bytes will be written
     *
     * @param  value
     *         The float value to be written
     *
     * @return  This buffer
     *
     * @throws  IndexOutOfBoundsException
     *          If {@code index} is negative
     *          or not smaller than the buffer's limit,
     *          minus three
     *
     * @throws  ReadOnlyBufferException
     *          If this buffer is read-only
     */
    public abstract ByteBuffer putFloat(int index, float value);

    /**
     * Creates a view of this byte buffer as a float buffer.
     *
     * <p> The content of the new buffer will start at this buffer's current
     * position.  Changes to this buffer's content will be visible in the new
     * buffer, and vice versa; the two buffers' position, limit, and mark
     * values will be independent.
     *
     * <p> The new buffer's position will be zero, its capacity and its limit
     * will be the number of bytes remaining in this buffer divided by
     * four, its mark will be undefined, and its byte order will be that
     * of the byte buffer at the moment the view is created.  The new buffer
     * will be direct if, and only if, this buffer is direct, and it will be
     * read-only if, and only if, this buffer is read-only.  </p>
     *
     * @return  A new float buffer
     */
    public abstract FloatBuffer asFloatBuffer();


    /**
     * Relative <i>get</i> method for reading a double value.
     *
     * <p> Reads the next eight bytes at this buffer's current position,
     * composing them into a double value according to the current byte order,
     * and then increments the position by eight.  </p>
     *
     * @return  The double value at the buffer's current position
     *
     * @throws  BufferUnderflowException
     *          If there are fewer than eight bytes
     *          remaining in this buffer
     */
    public abstract double getDouble();

    /**
     * Relative <i>put</i> method for writing a double
     * value&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> Writes eight bytes containing the given double value, in the
     * current byte order, into this buffer at the current position, and then
     * increments the position by eight.  </p>
     *
     * @param  value
     *         The double value to be written
     *
     * @return  This buffer
     *
     * @throws  BufferOverflowException
     *          If there are fewer than eight bytes
     *          remaining in this buffer
     *
     * @throws  ReadOnlyBufferException
     *          If this buffer is read-only
     */
    public abstract ByteBuffer putDouble(double value);

    /**
     * Absolute <i>get</i> method for reading a double value.
     *
     * <p> Reads eight bytes at the given index, composing them into a
     * double value according to the current byte order.  </p>
     *
     * @param  index
     *         The index from which the bytes will be read
     *
     * @return  The double value at the given index
     *
     * @throws  IndexOutOfBoundsException
     *          If {@code index} is negative
     *          or not smaller than the buffer's limit,
     *          minus seven
     */
    public abstract double getDouble(int index);

    /**
     * Absolute <i>put</i> method for writing a double
     * value&nbsp;&nbsp;<i>(optional operation)</i>.
     *
     * <p> Writes eight bytes containing the given double value, in the
     * current byte order, into this buffer at the given index.  </p>
     *
     * @param  index
     *         The index at which the bytes will be written
     *
     * @param  value
     *         The double value to be written
     *
     * @return  This buffer
     *
     * @throws  IndexOutOfBoundsException
     *          If {@code index} is negative
     *          or not smaller than the buffer's limit,
     *          minus seven
     *
     * @throws  ReadOnlyBufferException
     *          If this buffer is read-only
     */
    public abstract ByteBuffer putDouble(int index, double value);

    /**
     * Creates a view of this byte buffer as a double buffer.
     *
     * <p> The content of the new buffer will start at this buffer's current
     * position.  Changes to this buffer's content will be visible in the new
     * buffer, and vice versa; the two buffers' position, limit, and mark
     * values will be independent.
     *
     * <p> The new buffer's position will be zero, its capacity and its limit
     * will be the number of bytes remaining in this buffer divided by
     * eight, its mark will be undefined, and its byte order will be that
     * of the byte buffer at the moment the view is created.  The new buffer
     * will be direct if, and only if, this buffer is direct, and it will be
     * read-only if, and only if, this buffer is read-only.  </p>
     *
     * @return  A new double buffer
     */
    public abstract DoubleBuffer asDoubleBuffer();

}
