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

public class FormatData_ar_IQ extends ListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] metaValue_MonthNames = new String[] {
            "\u0643\u0627\u0646\u0648\u0646 \u0627\u0644\u062b\u0627\u0646\u064a",
            "\u0634\u0628\u0627\u0637",
            "\u0622\u0630\u0627\u0631",
            "\u0646\u064a\u0633\u0627\u0646",
            "\u0623\u064a\u0627\u0631",
            "\u062d\u0632\u064a\u0631\u0627\u0646",
            "\u062a\u0645\u0648\u0632",
            "\u0622\u0628",
            "\u0623\u064a\u0644\u0648\u0644",
            "\u062a\u0634\u0631\u064a\u0646 \u0627\u0644\u0623\u0648\u0644",
            "\u062a\u0634\u0631\u064a\u0646 \u0627\u0644\u062b\u0627\u0646\u064a",
            "\u0643\u0627\u0646\u0648\u0646 \u0627\u0644\u0623\u0648\u0644",
            "",
        };
        final String[] metaValue_MonthNarrows = new String[] {
            "\u0643",
            "\u0634",
            "\u0622",
            "\u0646",
            "\u0623",
            "\u062d",
            "\u062a",
            "\u0622",
            "\u0623",
            "\u062a",
            "\u062a",
            "\u0643",
            "",
        };
        final String[] metaValue_MonthAbbreviations = new String[] {
            "\u0643\u0627\u0646\u0648\u0646 \u0627\u0644\u062b\u0627\u0646\u064a",
            "\u0634\u0628\u0627\u0637",
            "\u0622\u0630\u0627\u0631",
            "\u0646\u064a\u0633\u0627\u0646",
            "\u0623\u064a\u0627\u0631",
            "\u062d\u0632\u064a\u0631\u0627\u0646",
            "\u062a\u0645\u0648\u0632",
            "\u0622\u0628",
            "\u0623\u064a\u0644\u0648\u0644",
            "\u062a\u0634\u0631\u064a\u0646\u00a0\u0627\u0644\u0623\u0648\u0644",
            "\u062a\u0634\u0631\u064a\u0646 \u0627\u0644\u062b\u0627\u0646\u064a",
            "\u0643\u0627\u0646\u0648\u0646 \u0627\u0644\u0623\u0648\u0644",
            "",
        };
        final Object[][] data = new Object[][] {
            { "MonthNames", metaValue_MonthNames },
            { "standalone.MonthNames", metaValue_MonthNames },
            { "MonthAbbreviations", metaValue_MonthAbbreviations },
            { "standalone.MonthAbbreviations", metaValue_MonthNames },
            { "MonthNarrows", metaValue_MonthNarrows },
            { "standalone.MonthNarrows", metaValue_MonthNarrows },
            { "buddhist.MonthNames", metaValue_MonthNames },
            { "buddhist.MonthAbbreviations", metaValue_MonthAbbreviations },
            { "buddhist.MonthNarrows", metaValue_MonthNarrows },
            { "japanese.MonthNames", metaValue_MonthNames },
            { "japanese.MonthAbbreviations", metaValue_MonthAbbreviations },
            { "japanese.MonthNarrows", metaValue_MonthNarrows },
            { "roc.MonthNames", metaValue_MonthNames },
            { "roc.MonthAbbreviations", metaValue_MonthAbbreviations },
            { "roc.MonthNarrows", metaValue_MonthNarrows },
        };
        return data;
    }
}
