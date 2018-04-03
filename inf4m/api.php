<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST'){
    $conexao = mysqli_connect('localhost', 'root', 'bcd127', 'dbusuario');

    $user=$_POST["user"];
    $pass=$_POST["pass"];

    $sql = "select * from tbl_usuario where usuario = '$user' and senha = '$pass'";

    $resultado = mysqli_query($conexao, $sql);

   if ($usuario = mysqli_fetch_assoc($resultado)){
        echo json_encode(array(
					"mensagem"=> true,
                    "usuario"=>$usuario));
    }else{
       echo json_encode(array(
					"mensagem"=> false,
                    "usuario"=>''));
   }
}
?>