<template>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                
                <ul class="nav nav-pills nav-justified">
                    <li class="nav-item">
                        <a class="nav-link" href="#" v-bind:class="{'active':activeTab===0}" v-on:click="activeTab=0">
                            Leer
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#" v-bind:class="{'active':activeTab===1}" v-on:click="activeTab=1">
                            Crear
                        </a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane container active" v-if="activeTab===1">
                        <div class="card card-default">
                            <div class="card-header">
                                <h1>
                                    Trayectorias (Crear)
                                </h1>
                            </div>
                            <div class="card-body">
                                <form v-on:submit.prevent="create">
                                    <div v-if="creating">
                                        Creando ...
                                    </div>
                                    <table class="table">
                                        <tr>
                                            <td>
                                                Latitud
                                            </td>
                                            <td>
                                                <input class="form-control" type="number" v-model="create_latitude">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                Longitud
                                            </td>
                                            <td>
                                                <input class="form-control" type="number" v-model="create_longitude">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                Velocidad
                                            </td>
                                            <td>
                                                <input class="form-control" type="number" v-model="create_speed">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                Fecha
                                            </td>
                                            <td>
                                                <input class="form-control" type="datetime-local" v-model="create_date">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                Id sector
                                            </td>
                                            <td>
                                                <input class="form-control" type="number" v-model="create_sector">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                Id tipo vehiculo
                                            </td>
                                            <td>
                                                <input class="form-control" type="number" v-model="create_vehicle_type">
                                            </td>
                                        </tr>
                                        <tr>
                                            <button class="btn btn-primary">
                                                Crear
                                            </button>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane container active" v-if="activeTab===0">
                        <div class="card card-default">
                            <div class="card-header">
                                <h1>
                                    Trayectorias (Buscar)
                                </h1>
                            </div>
                            <div class="card-body">
                                <form>
                                    <div v-if="loading">
                                        Buscando ...
                                    </div>
                                    <table class="table">
                                        <tr>
                                            <td>
                                                Desde
                                            </td>
                                            <td>
                                                <input class="form-control" type="datetime-local" v-model="search_from">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                Hasta
                                            </td>
                                            <td>
                                                <input class="form-control" type="datetime-local" v-model="search_to">
                                            </td>
                                        </tr>
                                        <tr>
                                            <button class="btn btn-primary" v-on:click.prevent="searchByDate">
                                                Buscar
                                            </button>
                                        </tr>
                                    </table>
                                </form>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>
                                                Latitud
                                            </th>
                                            <th>
                                                Longitud
                                            </th>
                                            <th>
                                                Velocidad
                                            </th>
                                            <th>
                                                Fecha
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr v-bind:key='trajectory.id_trayectoria' v-for="trajectory in trajectories">
                                            <td>
                                                {{trajectory.latitud}}
                                            </td>
                                            <td>
                                                {{trajectory.longitud}}
                                            </td>
                                            <td>
                                                {{trajectory.velocidad}}
                                            </td>
                                            <td>
                                                {{trajectory.fecha}}
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                activeTab: 0,

                trajectories: [],
                search_from: null,
                search_to: null,
                loading: false,

                creating: false,
                create_latitude: null,
                create_longitude: null,
                create_speed: null,
                create_date: null,
                create_sector: null,
                create_vehicle_type: null
            }
        },
        methods: {
            searchByDate() {
                this.loading = true
                this.getTrajectories(this.search_from, this.search_to)
            },
            create() {
                this.creating = true
                this.createTrajectory(this.create_latitude,this.create_longitude,this.create_date,this.create_speed,this.create_sector,this.create_vehicle_type)
            },
            createTrajectory(latitude, longitude, date, speed, sector, vehicle) {
                axios.get(`http://127.0.0.1:8000/crear/trayectoria/?latitud=${latitude}&longitud=${longitude}&velocidad=${speed}&fecha=${date}&id_sector=${sector}&id_tipo_vehiculo=${vehicle}`).then(response => {
                    this.create_latitude = null
                    this.create_longitude = null
                    this.create_date = null
                    this.create_speed = null
                    this.create_sector = null
                    this.create_vehicle_type = null
                    alert('creado')
                }).catch(error => {

                }).finally(() => {
                    this.creating = false
                })
            },
            getTrajectories(from, to) {
                axios.get('http://127.0.0.1:8000/buscar/fecha?inicio='+from+'&final='+to).then(response => {
                    this.trajectories = response.data
                }).catch(error => {

                }).finally(() => {
                    this.loading = false
                })
            }
        }
    }
</script>
