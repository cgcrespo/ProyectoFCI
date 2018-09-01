<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Trajectory extends Model
{
    protected $table='trayectoria_gye';
    public $timestamps=false;
    protected $primaryKey='id_trayectoria';
    protected $fillable=[
        'latitud','longitud','velocidad','fecha','id_sector','id_tipo_vehiculo'
    ];
}
