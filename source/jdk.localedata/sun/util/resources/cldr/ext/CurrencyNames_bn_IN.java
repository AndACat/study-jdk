/*
 * Copyright (c) 2012, 2025, Oracle and/or its affiliates. All rights reserved.
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

/*
 * COPYRIGHT AND PERMISSION NOTICE
 *
 * Copyright (c) 1991-2022 Unicode, Inc. All rights reserved.
 * Distributed under the Terms of Use in https://www.unicode.org/copyright.html.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of the Unicode data files and any associated documentation
 * (the "Data Files") or Unicode software and any associated documentation
 * (the "Software") to deal in the Data Files or Software
 * without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, and/or sell copies of
 * the Data Files or Software, and to permit persons to whom the Data Files
 * or Software are furnished to do so, provided that either
 * (a) this copyright and permission notice appear with all copies
 * of the Data Files or Software, or
 * (b) this copyright and permission notice appear in associated
 * Documentation.
 *
 * THE DATA FILES AND SOFTWARE ARE PROVIDED "AS IS", WITHOUT WARRANTY OF
 * ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT OF THIRD PARTY RIGHTS.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR HOLDERS INCLUDED IN THIS
 * NOTICE BE LIABLE FOR ANY CLAIM, OR ANY SPECIAL INDIRECT OR CONSEQUENTIAL
 * DAMAGES, OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE,
 * DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER
 * TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 * PERFORMANCE OF THE DATA FILES OR SOFTWARE.
 *
 * Except as contained in this notice, the name of a copyright holder
 * shall not be used in advertising or otherwise to promote the sale,
 * use or other dealings in these Data Files or Software without prior
 * written authorization of the copyright holder.
 */

package sun.util.resources.cldr.ext;

import sun.util.resources.OpenListResourceBundle;

public class CurrencyNames_bn_IN extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final Object[][] data = new Object[][] {
            { "USD", "$" },
            { "ang", "\u09a8\u09c7\u09a6\u09be\u09b0\u09b2\u09cd\u09af\u09be\u09a8\u09cd\u09a1\u09b8 \u0985\u09cd\u09af\u09be\u09a8\u09cd\u099f\u09bf\u09b2\u09bf\u09af\u09bc\u09be\u09a8 \u0997\u09bf\u09b2\u09cd\u09a1\u09be\u09b0" },
            { "awg", "\u0986\u09b0\u09c1\u09ac\u09be\u09a8 \u0997\u09bf\u09b2\u09cd\u09a1\u09be\u09b0" },
            { "bmd", "\u09ac\u09be\u09b0\u09ae\u09c1\u09a1\u09be\u09a8 \u09a1\u09b2\u09be\u09b0" },
            { "gtq", "\u0997\u09c1\u09af\u09bc\u09be\u09a4\u09c7\u09ae\u09be\u09b2\u09be\u09a8 \u0995\u09c7\u09ce\u09b8\u09be\u09b2" },
            { "hnl", "\u09b9\u09a8\u09cd\u09a1\u09c1\u09b0\u09be\u09a8 \u09b2\u09c7\u09ae\u09cd\u09aa\u09bf\u09b0\u09be" },
            { "htg", "\u09b9\u09be\u0987\u09a4\u09bf\u09af\u09bc\u09be\u09a8 \u0997\u09c1\u09b0\u09cd\u09a6" },
            { "mxn", "\u09ae\u09c7\u0995\u09cd\u09b8\u09bf\u0995\u09be\u09a8 \u09aa\u09c7\u09b8\u09cb" },
            { "xcd", "\u09aa\u09c2\u09b0\u09cd\u09ac \u0995\u09cd\u09af\u09be\u09b0\u09bf\u09ac\u09bf\u09af\u09bc\u09be\u09a8 \u09a1\u09b2\u09be\u09b0" },
        };
        return data;
    }
}
