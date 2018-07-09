<div class="container">
  <%@include file="menu.jsp"%>
  <img class="profileImage" src="${photoPath}" alt="test">
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
  <br/><br/>
  <table id="userWorkout">
    <tr>
      <th>Date</th>
      <th>Type</th>
      <th>Time in Minutes</th>
      <th>Heart Rate</th>
      <th>Calories Burned</th>
    </tr>
    <c:forEach var="item" items="${workoutList.workoutList}">
      <tr class="userWorkout">
        <td>${item.date}</td>
        <td>${item.type}</td>
        <td>${item.time}</td>
        <td>${item.heartRate}</td>
        <td>${item.caloriesBurned}</td>
      </tr>
    </c:forEach>
  </table>



</div>