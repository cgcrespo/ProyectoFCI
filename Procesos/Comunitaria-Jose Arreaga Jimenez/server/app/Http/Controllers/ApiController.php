<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Trajectory;

class ApiController extends Controller
{
    function searchByDate(Request $request)
    {
        $trajectories=Trajectory::where('fecha','>=',$request->inicio)->where('fecha','<=',$request->final)->get();
        return response()->json($trajectories);
    }
    function createTrejectory(Request $request)
    {
        Trajectory::create([
            'latitud'=>$request->latitud,
            'longitud'=>$request->longitud,
            'velocidad'=>$request->velocidad,
            'fecha'=>$request->fecha,
            'id_sector'=>$request->id_sector,
            'id_tipo_vehiculo'=>$request->id_tipo_vehiculo,
        ]);
    }
}
