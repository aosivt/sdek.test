<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
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
    <div class="container">
      <div class="row align-items-center">
        <div class="col"></div>
          <div class="col">
           <div class="card" style="width: 36rem;">
             <div class="card-body">
               <div class="card-header">Страничка отмены заказа</div>
                <form:form method="POST" action="/add/FileFormat" commandName="order">
                  <div class="form-group">
                    <label for="nOrder">Номер заказа</label>
                    <form:input path="orderNum" class="form-control" id="nOrder" placeholder="Введите номер отменяемого заказа"/>
                  </div>
                 <input type="submit" id="send" value="Не успеваю" class="btn btn-primary"/>
                </form:form>
                </div>
              </div>
            </div>
          <div class="col"></div>
        </div>
      </div>
    </body>
</html>