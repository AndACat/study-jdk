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

public class LocaleNames_su extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String metaValue_DE = "J\u00e9rman";
        final String metaValue_FR = "Prancis";
        final String metaValue_IT = "Italia";
        final String metaValue_JP = "Jepang";
        final String metaValue_RU = "Rusia";
        final String metaValue_CN = "Tiongkok";
        final Object[][] data = new Object[][] {
            { "BR", "Brasil" },
            { "CN", metaValue_CN },
            { "DE", metaValue_DE },
            { "FR", metaValue_FR },
            { "GB", "Britania Raya" },
            { "ID", "Indonesia" },
            { "IN", "India" },
            { "IT", metaValue_IT },
            { "JP", metaValue_JP },
            { "RU", metaValue_RU },
            { "US", "Am\u00e9rika Sarikat" },
            { "ZZ", "Wilayah Teu Dikenal" },
            { "de", metaValue_DE },
            { "en", "Inggris" },
            { "es", "Spanyol" },
            { "fr", metaValue_FR },
            { "it", metaValue_IT },
            { "ja", metaValue_JP },
            { "pt", "Portugis" },
            { "ru", metaValue_RU },
            { "su", "Basa Sunda" },
            { "zh", metaValue_CN },
            { "und", "Basa teu dikenal" },
            { "Cyrl", "Sirilik" },
            { "Hans", "Sederhana" },
            { "Hant", "Tradisional" },
            { "Latn", "Latin" },
            { "Zxxx", "Non-tulisan" },
            { "Zzzz", "Tulisan Teu Dikenal" },
            { "de_AT", "J\u00e9rman Austria" },
            { "de_CH", "J\u00e9rman Swiss Luhur" },
            { "en_AU", "Inggris Australia" },
            { "en_CA", "Inggris Kanada" },
            { "en_US", "Inggris Amerika" },
            { "es_ES", "Spanyol \u00c9ropa" },
            { "es_MX", "Spanyol M\u00e9ksiko" },
            { "fr_CA", "Prancis Kanada" },
            { "fr_CH", "Prancis Swiss" },
            { "pt_BR", "Portugis Brasil" },
            { "pt_PT", "Portugis \u00c9ropa" },
            { "es_419", "Spanyol Am\u00e9rika Latin" },
            { "zh_Hans", "Tiongkok Sederhana" },
            { "zh_Hant", "Tiongkok Tradisional" },
            { "type.nu.latn", "Digit Barat" },
            { "type.co.standard", "Aturan Runtuyan Standar" },
            { "type.ca.gregorian", "Kal\u00e9nder Gr\u00e9gorian" },
        };
        return data;
    }
}
