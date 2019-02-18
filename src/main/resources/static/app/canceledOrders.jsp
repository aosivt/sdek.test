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
<div class="container">
  <div class="row align-items-center">
    <div class="col"></div>
    <div class="col">
      <div class="card" style="width: 36rem;">
        <div class="card-body">
          <div class="card-header">Отмененные заказы</div>

          <input class="form-control" id="myInput" type="text" placeholder="Фильтр">
          <br>

          <table class="table table-bordered">
            <thead>
              <tr>
                <th>Номер заказа</th>
                <th>Дата добавления</th>
              </tr>
            </thead>
            <tbody id="canceledOrder">
              <c:forEach items="${canceledOrders}" var="canceledOrder">
                <tr>
                  <td>${canceledOrder.order.orderNum}</td>
                  <td>${canceledOrder.cancelDate}</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <div class="col"></div>
  </div>
</div>

</body>
<script>
  $(document).ready(function () {
    $("#myInput").on("keyup", function () {
      var value = $(this).val().toLowerCase();
      $("#canceledOrder tr").filter(function () {
        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
      });
    });
  });
</script>

</html>