/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codelabs.state.todo

import com.codelabs.state.util.generateRandomTodoItem
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class TodoViewModelTest {

    @Test
    fun whenAddingItem_updatesList(){
        //given
        val viewModel = TodoViewModel()
        //when: new item added
        val item1 = generateRandomTodoItem()
        viewModel.addItem(item1)
        //then
        assertThat(viewModel.todoItems).isEqualTo(listOf(item1))
    }

    @Test
    fun whenRemovingItem_updatesList(){
        //given
        val viewModel = TodoViewModel()
        val item1 = generateRandomTodoItem()
        val item2 = generateRandomTodoItem()
        viewModel.addItem(item1)
        viewModel.addItem(item2)

        //when
        viewModel.removeItem(item1)

        //then
        assertThat(viewModel.todoItems).isEqualTo(listOf(item2))
    }

    @Test
    fun whenEditing_currentItemUpdates(){
        //given
        val viewModel = TodoViewModel()
        val item1 = generateRandomTodoItem()
        val item2 = generateRandomTodoItem()
        viewModel.addItem(item1)
        viewModel.addItem(item2)
        //when
        val expected = item1.copy(task = "new task")
        viewModel.onEditItemSelected(item1)
        viewModel.onEditItemChange(expected)
        //then
        assertThat(viewModel.todoItems).isEqualTo(listOf(expected, item2))
    }

    @Test
    fun whenEditDone_currentItemNull(){
        //given
        val viewModel = TodoViewModel()
        val item1 = generateRandomTodoItem()
        val item2 = generateRandomTodoItem()
        viewModel.addItem(item1)
        viewModel.addItem(item2)
        //when
        val expected = item1.copy(task = "new task")
        viewModel.onEditItemSelected(item1)
        viewModel.onEditItemChange(expected)
        viewModel.onEditDone()
        //then
        assertThat(viewModel.currentEditItem).isEqualTo(null)
    }

}
