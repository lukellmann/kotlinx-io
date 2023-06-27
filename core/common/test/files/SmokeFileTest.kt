/*
 * Copyright 2017-2023 JetBrains s.r.o. and respective authors and developers.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the LICENCE file.
 */

package kotlinx.io.files

import kotlinx.io.createTempFile
import kotlinx.io.deleteFile
import kotlinx.io.readUtf8Line
import kotlinx.io.writeUtf8
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SmokeFileTest {
    private var tempFile: String? = null

    @BeforeTest
    fun setup() {
        tempFile = createTempFile()
    }

    @AfterTest
    fun cleanup() {
        deleteFile(tempFile!!)
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun testBasicFile() {
        val path = Path(tempFile!!)
        path.sink().use {
            it.writeUtf8("example")
        }

        path.source().use {
            assertEquals("example", it.readUtf8Line())
        }
    }
}
