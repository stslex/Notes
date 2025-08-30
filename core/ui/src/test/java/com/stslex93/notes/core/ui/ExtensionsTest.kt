package com.stslex93.notes.core.ui

import com.stslex93.notes.core.ui.Extensions.addItem
import com.stslex93.notes.core.ui.Extensions.removeItem
import kotlinx.collections.immutable.persistentSetOf
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test;

internal class ExtensionsTest {

    @Test
    fun `remove item from persistentSet`() {
        val testSet = persistentSetOf("1", "2", "3", "4")
        val resultSet = testSet.removeItem("2")
        Assertions.assertTrue(resultSet.size == 3)
        Assertions.assertEquals(persistentSetOf("1", "3", "4"), resultSet)
    }

    @Test
    fun `remove item which don't exist in persistentSet`() {
        val testSet = persistentSetOf("1", "2", "3", "4")
        val resultSet = testSet.removeItem("10")
        Assertions.assertTrue(resultSet.size == 4)
        Assertions.assertEquals(persistentSetOf("1", "2", "3", "4"), resultSet)
    }

    @Test
    fun `add new item to persistentSet`() {
        val testSet = persistentSetOf("1", "2", "3")
        val resultSet = testSet.addItem("4")
        Assertions.assertTrue(resultSet.size == 4)
        Assertions.assertEquals(persistentSetOf("1", "2", "3", "4"), resultSet)
    }

    @Test
    fun `add item which already exist to persistentSet`() {
        val testSet = persistentSetOf("1", "2", "3")
        val resultSet = testSet.addItem("2")
        Assertions.assertTrue(resultSet.size == 3)
        Assertions.assertEquals(persistentSetOf("1", "2", "3"), resultSet)
    }

}