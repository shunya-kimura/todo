<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="model.dto.TodoDTO" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todoリスト</title>
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
    <!-- 検索フォーム -->
    <form action="list" method="get">
        <label>タイトルで検索:</label>
        <input type="text" name="titleSearch">
        <button type="submit">検索</button>
    </form>
    <h2>Todoリスト</h2>
    <table border="1">
        <tr>
            <th>タイトル</th>
            <th>日付</th>
            <th>優先度</th>
        </tr>
        <% 
            List<TodoDTO> todoList = (List<TodoDTO>)request.getAttribute("todoList");
            List<TodoDTO> searchList = (List<TodoDTO>)request.getAttribute("searchList");
            
            if (searchList != null && !searchList.isEmpty()) {
                for (TodoDTO todo : searchList) {
        %>
        <tr>
            <td><a href='show?id=<%= todo.getId() %>'><%= todo.getTitle() %></a></td>
            <td><%= todo.getYmd() %></td>
            <td><%= todo.getPriority() %></td>
        </tr>
        <% 
                }
            } else {
                for (TodoDTO todo : todoList) {
        %>
        <tr>
            <td><a href='show?id=<%= todo.getId() %>'><%= todo.getTitle() %></a></td>
            <td><%= todo.getYmd() %></td>
            <td><%= todo.getPriority() %></td>
        </tr>
        <% 
                }
            }
        %>
    </table>

    <div class="button-container">
        <button><a href="list?sort=asc">日付昇順</a></button>
        <button><a href="list?sort=desc">日付降順</a></button>
        <button><a href="list?sort=priority_asc">優先度昇順</a></button>
        <button><a href="list?sort=priority_desc">優先度降順</a></button>
    </div>

    <button><a href="new">新規作成</a></button> 
    <form method="POST" action="logout">
        <input type="submit" value="Logout">
    </form>
</body>
</html>
