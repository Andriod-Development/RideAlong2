<?php

require_once 'Functions.php';

$fun = new Functions();


if ($_SERVER['REQUEST_METHOD'] == 'POST')
{
  $data = json_decode(file_get_contents("php://input"));

  if(isset($data -> operation)){

  	$operation = $data -> operation;

  	if(!empty($operation)){

  		if($operation == 'register'){

  			if(isset($data -> user ) && !empty($data -> user) && isset($data -> user -> name) 
  				&& isset($data -> user -> email) && isset($data -> user -> password)){

  				$user = $data -> user;
  				$name = $user -> name;
  				$email = $user -> email;
  				$password = $user -> password;

          if ($fun -> isEmailValid($email)) {
            
            echo $fun -> registerUser($name, $email, $password);

          } else {

            echo $fun -> getMsgInvalidEmail();
          }

  			} else {

  				echo $fun -> getMsgInvalidParam();

  			}

  		}else if ($operation == 'login') {

        if(isset($data -> user ) && !empty($data -> user) && isset($data -> user -> email) && isset($data -> user -> password)){

          $user = $data -> user;
          $email = $user -> email;
          $password = $user -> password;

          echo $fun -> loginUser($email, $password);

        } else {

          echo $fun -> getMsgInvalidParam();

        }
      } 

else if($operation == 'driver_travel_info'){

  			/*
			if(isset($data -> driverDetails ) && !empty($data -> driverDetails) && isset($data -> driverDetails -> userid) 
  				&& isset($data -> driverDetails -> car_no) && isset($data -> driverDetails -> car_model)
			&& isset($data -> driverDetails -> from_place) && isset($data -> driverDetails -> destination)
			&& isset($data -> driverDetails -> LeavingDate)){
*/
  				$driverDetails = $data -> driverDetails;
				
  				$userid = $driverDetails -> userId;
				
  				$car_no = $driverDetails -> car_no;
  				$car_model = $driverDetails -> carModel;
				$from_place = $driverDetails -> from_place;
				$destination = $driverDetails -> destination;
				$LeavingDate = $driverDetails -> leavingDate;

         
            
            echo $fun -> registerDriver($userid,$car_no,$car_model,$from_place,$destination,$LeavingDate);

          

  			//} 

  		}

else if ($operation == 'chgPass') {

        if(isset($data -> user ) && !empty($data -> user) && isset($data -> user -> email) && isset($data -> user -> old_password) 
          && isset($data -> user -> new_password)){

          $user = $data -> user;
          $email = $user -> email;
          $old_password = $user -> old_password;
          $new_password = $user -> new_password;

          echo $fun -> changePassword($email, $old_password, $new_password);

        } else {

          echo $fun -> getMsgInvalidParam();

        }
      }

  	}else{

  		
  		echo $fun -> getMsgParamNotEmpty();

  	}
  } else {

  		echo $fun -> getMsgInvalidParam();

  }
} else if ($_SERVER['REQUEST_METHOD'] == 'GET'){


  echo "<h1>Lewebev RideAlong API</h1>";

}

