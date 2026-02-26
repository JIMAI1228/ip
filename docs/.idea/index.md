---
layout: default
title: Chloe User Guide
---
# Chloe User Guide

Chloe is a simple tacks tracking chatbot with a GUI. Users are able to add tasks, list them, find a certain task by keyword, mark and unmark completion, and delete tasks including multiple tasks at once.

## Quick start
1. Launch the app by running provided JAR.
2. Type a command in the input box and press **Enter** or click **Send**.

## Command Summary
| Action       | Command format                                            | Example                                            |
|--------------|-----------------------------------------------------------|----------------------------------------------------|
| Add todo     | `todo DESCRIPTION`                                        | `todo read book`                                   |
| Add deadline | `deadline DESCRIPTION /by d/M/yyyy HHmm`                  | `deadline homework /by 20/2/2026 2359`             |
| Add event    | `event DESCRIPTION /from d/M/yyyy HHmm /to d/M/yyyy HHmm` | `event exam /from 1/6/2026 1600 /to 1/6/2026 1800` |
| List tasks   | `list`                                                    | `list`                                             |
| Find tasks   | `find KEYWORD`                                            | `find homework`                                    |
| Mark done    | `mark INDEX` or `mark INDEXES`                            | `mark 2` / `mark 1 3 5`                            |
| Unmark       | `unmark INDEX` or `unmark INDEXES`                        | `unmark 2` / `unmark 1 3`                          |
| Delete       | `delete INDEX` or `delete INDEXES`                        | `delete 2` / `delete 1 4 7`                        |
| Search help  | `help`                                                    | `help`                                             |
| Exit         | `bye`                                                     | `bye`                                              |

> Notes:
> - Indexes start from **1**(Use 'list' to see the index of each task).
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