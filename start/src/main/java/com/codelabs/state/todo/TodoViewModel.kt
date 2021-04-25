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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {

    private var currentEditPos by mutableStateOf(-1)

    var todoItems: List<TodoItem> by mutableStateOf(listOf())
        private set

    val currentEditItem: TodoItem?
        get() = todoItems.getOrNull(currentEditPos)

    fun addItem(item: TodoItem) {
        todoItems = todoItems + listOf(item)
    }

    fun removeItem(item: TodoItem) {
        todoItems = todoItems.toMutableList().also {
            it.remove(item)
        }
        onEditDone()
    }

    fun onEditItemSelected(item: TodoItem){
        currentEditPos = todoItems.indexOf(item)
    }

    fun onEditDone(){
        currentEditPos = -1
    }

    fun onEditItemChange(item:TodoItem){
        val currentItem = requireNotNull(currentEditItem)
        require(currentItem.id == item.id){
            "you can only change an item with the same id as currentEditItem"
        }

        todoItems = todoItems.toMutableList().also{
            it[currentEditPos] = item
        }
    }
}
