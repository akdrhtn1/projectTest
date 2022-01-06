const data = {
    isChked: false
}

const CertificationOn = "CERTIFICATIONON";
const CertificationOff = "CERTIFICATIONOFF";

export const certificationReducer = (state = data.isChked, action) => {
    if (action.type === CertificationOn) {
        return action.data;
    } else if (action.type === CertificationOff) {
        return action.data;
    }
    return state;
}

export const action_on = () => {
    return {
        type: CertificationOn,
        data: {
            isChked: true
        }
    }
};

export const action_off = () => {
    return {
        type: CertificationOff,
        data: {
            isChked: false
        }
    }
}


