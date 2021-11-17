import axios from 'axios';

const PARTICIPANTS_API_BASE_URL = "http://localhost:8080/api/v1/participant";

class ParticipantService {

    getParticipants(){
        return axios.get(PARTICIPANTS_API_BASE_URL+'/enroll');
    }

    createParticipant(participant){
        return axios.post(PARTICIPANTS_API_BASE_URL+'/enroll', participant);
    }

    getParticipantById(participantEmailId){
        return axios.get(PARTICIPANTS_API_BASE_URL + '/getByEmailId', {
                                                                  params: {
                                                                    emailId: participantEmailId
                                                                  }
                                                                });
    }

    updateParticipant(participant, emailId){
        return axios.put(PARTICIPANTS_API_BASE_URL + '/update/'+emailId, participant);
    }

    deleteEmployee(employeeId){
        return axios.delete(PARTICIPANTS_API_BASE_URL + '/' + employeeId);
    }
}

export default new ParticipantService()