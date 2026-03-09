# Chloe User Guide

Chloe is a simple tacks tracking chatbot with a GUI. Users are able to add tasks, list them, find a certain task by keyword, mark and unmark completion, and delete tasks including multiple tasks at once.

## Quick start
1. Launch the app by running provided JAR.
2. Type a command in the input box and press **Enter** or click **Send**.

## Command Summary

### Add todo
**Format:** `todo DESCRIPTION`  
**Example:** `todo read book`

### Add deadline
**Format:** `deadline DESCRIPTION /by dd/MM/yyyy HHmm`  
**Example:** `deadline homework /by 20/02/2026 2359`

### Add event
**Format:** `event DESCRIPTION /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm`  
**Example:** `event exam /from 01/06/2026 1600 /to 01/06/2026 1800`

### List tasks
**Format:** `list`  
**Example:** `list`

### Find tasks
**Format:** `find KEYWORD`  
Chloe lists all tasks whose description contains the given keyword **as a substring**.
For example, 'find a' matches tasks contains 'a' anywhere in the description (e.g., 'read', 'assignment').

**Example:** `find homework`

### Mark done (supports multiple indexes)
**Format:** `mark INDEX [INDEXES]`  
**Examples:** `mark 2`, `mark 1 3 5`

### Unmark (supports multiple indexes)
**Format:** `unmark INDEX [INDEXES]`  
**Examples:** `unmark 2`, `unmark 1 3`

### Delete (supports multiple indexes)
**Format:** `delete INDEX [INDEXES]`  
**Examples:** `delete 2`, `delete 1 4 7`

### Help
**Format:** `help`  
**Example:** `help`

### Exit
**Format:** `bye`  
**Example:** `bye`

> ***Notes:***
> - Indexes start from **1**(Use 'list' to see the index of each task).
> - The indexes used in 'mark', 'unmark' and 'delete' refers to the tasks' **current position in the 'list' output**. 
>   Index may change after deleting tasks.
> - Times use the format **dd/MM/yyyy HHmm**.
> - Chloe will show an error message if the date is invalid(e.g., 30/02/2026 1600).
> - Chloe will also show an error message if a duplicate task is entered.


## Features

### Adding Tasks
- **Todo**: store a task description.
- **Deadline**: store a task with a due time.
- **Event**: store a task with a starting and an end time(start must be before the end).

### Managing Tasks
- **List**: show all tasks with their index.
- **Mark / Unmark**: changes completion status of one or more tasks.
- **Delete**: remove one or more tasks at once.

### Finding Tasks 
- **Find**: filters tasks whose description contains the keyword.

### Getting help 
- **Help**: shows the list of supported commands and their usage.

### FAQ
- **Invalid date**: Check the format 'dd/MM/yyyy HHmm' and check whether the date exists(e.g., Feb 30 is invalid).