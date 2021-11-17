import React, { Component } from 'react'
import ParticipantService from '../services/ParticipantService'

class ViewParticipantComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            emailId: this.props.match.params.emailId,
            participant: {}
        }
    }

    componentDidMount(){
        this.state.emailId = this.props.location.state.emailId;
       /* ParticipantService.getParticipantById(this.state.emailId).then( res => {
            this.setState({participant: res.data});
        }) */
    }

    retrievePreviouslySavedParticipant = (e) => {
        e.preventDefault();
        ParticipantService.getParticipantById(this.state.emailId).then( res => {
                    this.setState({participant: res.data});
                })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3" style={{backgroundColor: 'powderblue'}}>
                    <h3 className = "text-center"> View Participant Details</h3>
                    <div className = "card-body" >
                        <div className = "row">
                            <label> Participant First Name: </label>
                            <div> <input placeholder="First Name" name="firstName" value={this.state.participant.firstName} /></div>
                        </div>
                        <div className = "row">
                            <label> Participant Last Name: </label>
                            <div> <input placeholder="Last Name" name="lastName" value={this.state.participant.lastName} /></div>
                        </div>
                        <div className = "row">
                            <label> Participant Email ID: </label>
                            <div> <input placeholder="Email Id" name="emailId" value={this.state.participant.emailId} /></div>
                        </div>
                        <div className = "row">
                            <label> Participant Date Of Joining: </label>
                            <div> <input placeholder="Date Of Joining" name="dateOfJoining" value={this.state.participant.dateOfJoining} /></div>
                        </div>

                        <button className="btn btn-success" style={{backgroundColor: 'blue'}} onClick={this.retrievePreviouslySavedParticipant}>Retrieve</button>
                    </div>

                </div>
            </div>
        )
    }
}

export default ViewParticipantComponent
