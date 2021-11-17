import React, { Component } from 'react'
import ParticipantService from '../services/ParticipantService';
import DatePicker from 'react-datepicker';

import "react-datepicker/dist/react-datepicker.css";
import 'bootstrap/dist/css/bootstrap.min.css';

class CreateParticipantComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            // step 2
            participants: [],
            id: this.props.match.params.id,
            firstName: '',
            lastName: '',
            emailId: '',
            dateOfJoining: new Date(),
            participantAdded:false
        }
        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.changeEmailHandler = this.changeEmailHandler.bind(this);
        this.saveOrUpdateEmployee = this.saveOrUpdateParticipant.bind(this);
    }

    // step 3
    componentDidMount(){

        // step 4
        if(this.state.id === '_add'){
            return
        }else{
            ParticipantService.getParticipantById(this.state.id).then( (res) =>{
                let participant = res.data;
                this.setState({firstName: participant.firstName,
                    lastName: participant.lastName,
                    emailId : participant.emailId,
                    dateOfJoining: participant.dateOfJoining
                });
            });
        }        
    }
    saveOrUpdateParticipant = (e) => {
        e.preventDefault();
        let employee = {firstName: this.state.firstName,
         lastName: this.state.lastName,
         emailId: this.state.emailId,
         dateOfJoining: this.state.dateOfJoining};
        console.log('Participant => ' + JSON.stringify(employee));

        // step 5
        //if(this.state.id === '_add'){
            ParticipantService.createParticipant(employee).then(res =>{
                this.props.history.push({pathname:'/view-participant/',state:{emailId:this.state.emailId}});
            });
        /* }else{
            ParticipantService.updateEmployee(employee, this.state.id).then( res => {
                this.props.history.push('/participants');
            });
        } */
    }
    
    changeFirstNameHandler= (event) => {
        this.setState({firstName: event.target.value});
    }

    changeLastNameHandler= (event) => {
        this.setState({lastName: event.target.value});
    }

    changeEmailHandler= (event) => {
        this.setState({emailId: event.target.value});
    }

    cancel(){
        this.props.history.push('/participants');
    }

    getTitle(){
        if(this.state.id !== '_add'){
            return <h3 className="text-center">Add Participant</h3>
        }else{
            return <h3 className="text-center">Update Participant</h3>
        }
    }
    handleChange = (event) =>{
        this.setState({dateOfJoining: event});
     }
    render() {
        return (
            <div>
                <br></br>
                   <div className = "container" >
                        <div className = "row" >
                            <div className = "card col-md-6 offset-md-3 offset-md-3"  style={{backgroundColor: 'powderblue'}}>
                                {
                                    this.getTitle()
                                }
                                <div className = "card-body" >
                                    <form>
                                        <div className = "form-group">
                                            <label> First Name: </label>
                                            <input placeholder="First Name" name="firstName" className="form-control" 
                                                value={this.state.firstName} onChange={this.changeFirstNameHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Last Name: </label>
                                            <input placeholder="Last Name" name="lastName" className="form-control" 
                                                value={this.state.lastName} onChange={this.changeLastNameHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Email Id: </label>
                                            <input placeholder="Email Address" name="emailId" className="form-control" 
                                                value={this.state.emailId} onChange={this.changeEmailHandler}/>
                                        </div>
                                        <div className="form-group">
                                             <label> Date Of Joining: </label>     <DatePicker
                                                      selected={ this.state.dateOfJoining }
                                                      onChange={ this.handleChange }
                                                      name="dateOfJoining"
                                                      dateFormat="MM/dd/yyyy"
                                                  />

                                         </div>
                                        <button className="btn btn-success" style={{backgroundColor: 'blue'}} onClick={this.saveOrUpdateEmployee}>Next</button>

                                    </form>
                                </div>
                            </div>
                        </div>

                   </div>
            </div>
        )
    }
}

export default CreateParticipantComponent
