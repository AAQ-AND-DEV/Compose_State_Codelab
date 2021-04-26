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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.codelabs.state.ui.StateCodelabTheme

class TodoActivity : AppCompatActivity() {

    val todoViewModel by viewModels<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateCodelabTheme {
                Surface {
                    TodoActivityScreen(vm = todoViewModel)
                }
            }
        }
    }

    @Composable
    fun TodoActivityScreen(vm: TodoViewModel){
//        //by automatically unwraps State<type> to regular type
//        //State vals can be used by Compose to trigger recomposition
//        val items : List<TodoItem> by vm.todoItems.observeAsState(listOf())
        TodoScreen(
            items = vm.todoItems,
            currentlyEditing = todoViewModel.currentEditItem,
            onAddItem = {vm.addItem(it)},
            onRemoveItem = {vm.removeItem(it)},
            onStartEdit = vm::onEditItemSelected,
            onEditItemChange = vm::onEditItemChange,
            onEditDone = vm::onEditDone
        )
    }
}
