<div class="container">
  <img class="profileImage" src="${user.photoLocation}" alt="test">
  <h1>Exercise Tracker</h1>
  <h4>Hello, ${user.firstName}</h4>
    <c:set var="gender" value="Male"/>
    <c:if test="${user.gender == 'f'}">
      <c:set var="gender" value="Female"/>
    </c:if>
  <table id="userInfo">
    <tr>
      <td>Username:</td>
      <td>${user.username}</td>
    </tr>
    <tr>
      <td>First name:</td>
      <td>${user.firstName}</td>
    </tr>
    <tr>
      <td>Last name:</td>
      <td>${user.lastName}</td>
    </tr>
    <tr>
      <td>Gender:</td>
      <td>${gender}</td>
    </tr>
    <tr>
      <td>Birthdate:</td>
      <td>${user.birthdate}</td>
    </tr>
    <tr>
      <td>Weight:</td>
      <td>${user.weight}</td>
    </tr>
  </table>


</div>