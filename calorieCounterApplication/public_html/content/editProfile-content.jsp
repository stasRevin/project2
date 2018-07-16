<div class="container">
  <%@include file="menu.jsp"%>
  <img class="mainImage" src="images/" alt="">
  <h1>Exercise Tracker</h1>
  <h4>Edit Your Profile</h4>

  <form action="/updadeProfile">

    Username:<br/>
    <input name="username" type="text" value="${user.username}">
    <br/><br/>
    First name:<br/>
    <input name="firstName" type="text" value="${user.firstName}">
    <br/><br/>
    Last name:<br/>
    <input name="lastName" type="text" value="${user.lastName}">
    <br/><br/>
    Gender:<br/>
    <c:set var="currentGender" scope="session" value="female"/>
    Current value (to change, select another value):
                   <c:if test="${user.gender eq 'm'}">
                     <c:set var="currentGender" scope="session" value="male"/>
                   </c:if>
        <c:out value="${currentGender}"/><br/>
        <input type="radio" name="gender" value="f" checked>Female<br/>
        <input type="radio" name="gender" value="m">Male

    <br/><br/>
    Birthdate:<br/>
    <input name="birthdate" type="date" min="1900-01-01" max="2100-01-01" value="${user.birthdate}">
    <br/><br/>
    Weight:<br/>
    <input name="weight" type="text" value="${user.weight}">
    <br/><br/>
    <input type="button" value="Update Profile">
  </form>

</div>