<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE HTML>
<html>
  <head>
      <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.3.0/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="/resources/app.js"></script>
    <script>$(window).load(connect());</script>
  </head>
  <body>
    <h1>Тестовая страница для проверке подключения к БД</h1>
    <input class="form-control" id="myInput" type="text" placeholder="Search..">
    <br>

      <table class="table table-bordered">
        <thead>
            <tr>
              <th>Заголовок</th>
              <th>Идетификатор</th>
            </tr>
        </thead>
        <tbody id="myTable">
        <c:forEach  items="${formats}" var ="format">
        <tr>
          <td>${format.title}</td>
          <td>${format.id}</td>
        </tr>
        </c:forEach>
        </tbody>
      </table>

  </body>
    <script>
    $(document).ready(function(){
      $("#myInput").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function() {
          $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
      });
    });
    </script>
</html>