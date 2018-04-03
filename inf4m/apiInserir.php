<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST'){
    $conexao = mysqli_connect('localhost', 'root', 'bcd127', 'dbusuario');

    $user=$_POST["user"];
    $pass=$_POST["pass"];
    $sexo=$_POST["sexo"];
    
    $sql = "insert into tbl_usuario set usuario = '$user', senha = '$pass', sexo = '$sexo' ";

    if($resultado = mysqli_query($conexao, $sql)){
        echo json_encode(array(
					"mensagem"=> true));
    }else{
        echo json_encode(array(
					"mensagem"=> false));
    }
}
?>