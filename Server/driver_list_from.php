<?php

	  $host = '127.0.0.1';
	  $user = 'leweqnnh_ride';
	 $db = 'leweqnnh_ridealong';
	  $pass = 'Lordofrings@1234';
	  $conn;

	  
	  $con = mysqli_connect($host,$user,$pass,$db);
$data = json_decode(file_get_contents("php://input"));



$passengerDetails = $data -> passengerDetails;
	$from= $passengerDetails -> from;
				echo "from---".$passengerDetails -> from;
  				$destination = $passengerDetails -> destination;


ven($from,$destination,$con);

function ven($from,$destination,$con){
	$userData1=array();
	error_log("from=",0);

	
	echo "dest".$destination;
	echo "from>>".$from;
	$sql="select * from passenger where from_place='".$from."'";
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