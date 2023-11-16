<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todoリスト</title>
</head>
<body>
    <h1>Todoリスト</h1>
    <% String message = (String)request.getAttribute("message"); %>
    <p><%= message %></p>
        
    <span><strong>ID</strong></span>
    <span><strong>タイトル</strong></span>
    <span><strong>日付</strong></span>
    <span><strong>優先度</strong></span><br>
    
    <% 
      ArrayList<HashMap<String, String>> rows = (ArrayList<HashMap<String, String>>)request.getAttribute("rows"); 
    %>

    <% 
      for (HashMap<String, String> columns : rows) {
    %>
    <span><%= columns.get("id") %></span>
    <span><a href='show?id=<%= columns.get("id") %>'><%= columns.get("title") %></a></span>
    <span><%= columns.get("ymd") %></span>
     <span><%= columns.get("priority") %></span><br>
    <% } %>
    
    <p><a href="list?sort=asc">日付昇順でソート</a></p>
    <p><a href="list?sort=desc">日付降順でソート</a></p>
    <p><a href="list?sort=priority_asc">優先度昇順でソート</a></p>
    <p><a href="list?sort=priority_desc">優先度降順でソート</a></p>
    
    <p><a href="new">新規作成</a></p> 
</body>
</html>
