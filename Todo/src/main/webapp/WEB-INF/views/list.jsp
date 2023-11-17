<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
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
    <h2>Todoリスト</h2>
    <table border="1">
        <tr>
            <th>タイトル</th>
            <th>日付</th>
            <th>優先度</th>
        </tr>
        <% 
            ArrayList<HashMap<String, String>> rows = (ArrayList<HashMap<String, String>>)request.getAttribute("rows"); 
            for (HashMap<String, String> columns : rows) {
        %>
        <tr>
            <td><a href='show?id=<%= columns.get("id") %>'><%= columns.get("title") %></a></td>
            <td><%= columns.get("ymd") %></td>
            <td><%= columns.get("priority") %></td>
        </tr>
        <% } %>
    </table>

    <div class="button-container">
        <button><a href="list?sort=asc">日付昇順</a></button>
        <button><a href="list?sort=desc">日付降順</a></button>
        <button><a href="list?sort=priority_asc">優先度昇順</a></button>
        <button><a href="list?sort=priority_desc">優先度降順</a></button>
    </div>

    <button><a href="new">新規作成</a></button> 
</body>
</html>
