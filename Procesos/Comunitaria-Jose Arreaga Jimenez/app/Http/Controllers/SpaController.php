<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Trajectory;

class SpaController extends Controller
{
    function index()
    {
        return view('spa');
    }
}
