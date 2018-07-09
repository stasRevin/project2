<div class="container">
  <%@include file="menu.jsp"%>
  <div id="logExerciseForm">
      <h3>Log a New Exercise</h3>
      <form action="/java114/logExercise" method="post">
      <div class="col-25">
        <label for="type">Type:</label>
      </div>
      <div class="col-75">
          <select name="type">
            <option value="1" selected>Running</option>
            <option value="2">Walking</option>
            <option value="3">Swimming</option>
            <option value="4">Weightlifting</option>
            <option value="5">Yoga</option>
            <option value="6">Dancing</option>
            <option value="7">Sport</option>
            <option value="8">Other</option>
          </select>
      </div>
      <br/><br/>
      <div class="col-25">
       <label for="date">Date:</label>
      </div>
      <div class="col-75">
        <input type="date" name="date" min="1900-01-01" max="2100-01-01" placeholder="Exercise Date">
      </div>
      <br/><br/>
      <div class="col-25">
        <label for="time">Time (in minutes):</label>
      </div>
      <div class="col-75">
        <input type="text" name="time">
      </div>
      <br/><br/>
      <div class="col-25">
        <label for="heartRate">Average Heart Rate:</label>
      </div>
      <div class="col-75">
        <input type="text" name="heartRate">
      </div>
      <br/><br/><br/>
      <input type="submit" value="LOG EXERCISE">
      </form>
  </div>

</div>