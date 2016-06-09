<?php

	  $host = '127.0.0.1';
	  $user = 'leweqnnh_ride';
	 $db = 'leweqnnh_ridealong';
	  $pass = 'Lordofrings@1234';
	  $conn;

	  
	  $con = mysqli_connect($host,$user,$pass,$db);
$data = json_decode(file_get_contents("php://input"));



$driverDetails = $data -> driverDetails;
	$from= $driverDetails -> from;
				echo "from---".$driverDetails -> from;
  				$destination = $driverDetails -> destination;


ven($from,$destination,$con);

function ven($from,$destination,$con){
	$userData1=array();
	error_log("from=",0);

	
	echo "dest".$destination;
	echo "from>>".$from;
	$sql="select * from driver where from_place='".$from."' and destination='".$destination."'";
	echo $sql;

	$query=mysqli_query($con,$sql);
	print_r($query);
	while($row=mysqli_fetch_assoc($query)){
		echo $row['userid'];
		array_push($userData1,$row);
	}
	
	$Juser=json_encode($userData1);
	print_r($Juser);
	return $Juser;
}

  				
?>