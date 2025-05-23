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

package sun.text.resources.cldr.ext;

import java.util.ListResourceBundle;

public class FormatData_el_POLYTON extends ListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] metaValue_MonthAbbreviations = new String[] {
            "\u0399\u03b1\u03bd",
            "\u03a6\u03b5\u03b2",
            "\u039c\u03b1\u03c1",
            "\u0391\u03c0\u03c1",
            "\u039c\u03b1\u0390",
            "\u0399\u03bf\u03c5\u03bd",
            "\u0399\u03bf\u03c5\u03bb",
            "\u0391\u1f50\u03b3",
            "\u03a3\u03b5\u03c0",
            "\u1f48\u03ba\u03c4",
            "\u039d\u03bf\u03b5",
            "\u0394\u03b5\u03ba",
            "",
        };
        final String[] metaValue_MonthNames = new String[] {
            "\u0399\u03b1\u03bd\u03bf\u03c5\u03b1\u03c1\u03af\u03bf\u03c5",
            "\u03a6\u03b5\u03b2\u03c1\u03bf\u03c5\u03b1\u03c1\u03af\u03bf\u03c5",
            "\u039c\u03b1\u03c1\u03c4\u03af\u03bf\u03c5",
            "\u0391\u03c0\u03c1\u03b9\u03bb\u03af\u03bf\u03c5",
            "\u039c\u03b1\u0390\u03bf\u03c5",
            "\u0399\u03bf\u03c5\u03bd\u03af\u03bf\u03c5",
            "\u0399\u03bf\u03c5\u03bb\u03af\u03bf\u03c5",
            "\u0391\u1f50\u03b3\u03bf\u03cd\u03c3\u03c4\u03bf\u03c5",
            "\u03a3\u03b5\u03c0\u03c4\u03b5\u03bc\u03b2\u03c1\u03af\u03bf\u03c5",
            "\u1f48\u03ba\u03c4\u03c9\u03b2\u03c1\u03af\u03bf\u03c5",
            "\u039d\u03bf\u03b5\u03bc\u03b2\u03c1\u03af\u03bf\u03c5",
            "\u0394\u03b5\u03ba\u03b5\u03bc\u03b2\u03c1\u03af\u03bf\u03c5",
            "",
        };
        final String metaValue_calendarname_gregorian = "\u0393\u03c1\u03b7\u03b3\u03bf\u03c1\u03b9\u03b1\u03bd\u1f78 \u1f21\u03bc\u03b5\u03c1\u03bf\u03bb\u03cc\u03b3\u03b9\u03bf";
        final Object[][] data = new Object[][] {
            { "MonthNames", metaValue_MonthNames },
            { "standalone.MonthNames",
                new String[] {
                    "\u0399\u03b1\u03bd\u03bf\u03c5\u03ac\u03c1\u03b9\u03bf\u03c2",
                    "\u03a6\u03b5\u03b2\u03c1\u03bf\u03c5\u03ac\u03c1\u03b9\u03bf\u03c2",
                    "\u039c\u03ac\u03c1\u03c4\u03b9\u03bf\u03c2",
                    "\u0391\u03c0\u03c1\u03af\u03bb\u03b9\u03bf\u03c2",
                    "\u039c\u03ac\u03b9\u03bf\u03c2",
                    "\u0399\u03bf\u03cd\u03bd\u03b9\u03bf\u03c2",
                    "\u0399\u03bf\u03cd\u03bb\u03b9\u03bf\u03c2",
                    "\u0391\u1f54\u03b3\u03bf\u03c5\u03c3\u03c4\u03bf\u03c2",
                    "\u03a3\u03b5\u03c0\u03c4\u03ad\u03bc\u03b2\u03c1\u03b9\u03bf\u03c2",
                    "\u1f48\u03ba\u03c4\u03ce\u03b2\u03c1\u03b9\u03bf\u03c2",
                    "\u039d\u03bf\u03ad\u03bc\u03b2\u03c1\u03b9\u03bf\u03c2",
                    "\u0394\u03b5\u03ba\u03ad\u03bc\u03b2\u03c1\u03b9\u03bf\u03c2",
                    "",
                }
            },
            { "MonthAbbreviations", metaValue_MonthAbbreviations },
            { "standalone.MonthAbbreviations", metaValue_MonthAbbreviations },
            { "buddhist.MonthNames", metaValue_MonthNames },
            { "buddhist.MonthAbbreviations", metaValue_MonthAbbreviations },
            { "japanese.MonthNames", metaValue_MonthNames },
            { "japanese.MonthAbbreviations", metaValue_MonthAbbreviations },
            { "roc.MonthNames", metaValue_MonthNames },
            { "roc.MonthAbbreviations", metaValue_MonthAbbreviations },
            { "calendarname.buddhist", "\u0392\u03bf\u03c5\u03b4\u03b9\u03c3\u03c4\u03b9\u03ba\u1f78 \u1f21\u03bc\u03b5\u03c1\u03bf\u03bb\u03cc\u03b3\u03b9\u03bf" },
            { "calendarname.islamic", "\u1f38\u03c3\u03bb\u03b1\u03bc\u03b9\u03ba\u1f78 \u1f21\u03bc\u03b5\u03c1\u03bf\u03bb\u03cc\u03b3\u03b9\u03bf" },
            { "calendarname.gregorian", metaValue_calendarname_gregorian },
            { "calendarname.gregory", metaValue_calendarname_gregorian },
            { "calendarname.japanese", "\u1f38\u03b1\u03c0\u03c9\u03bd\u03b9\u03ba\u1f78 \u1f21\u03bc\u03b5\u03c1\u03bf\u03bb\u03cc\u03b3\u03b9\u03bf" },
            { "calendarname.islamic-civil", "\u1f38\u03c3\u03bb\u03b1\u03bc\u03b9\u03ba\u1f78 \u1f00\u03c3\u03c4\u03b9\u03ba\u1f78 \u1f21\u03bc\u03b5\u03c1\u03bf\u03bb\u03cc\u03b3\u03b9\u03bf" },
        };
        return data;
    }
}
