function handleView() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(sendLocation, handleError);
    } else {
        $('#promptModal').modal('show')
    }
}

function sendLocation(position) {
    fetch(window.location.origin + '/locations/create?latitude=' + position.coords.latitude + '&longitude=' + position.coords.longitude);
}

function handleError(error) {
    switch(error.code) {
        case error.PERMISSION_DENIED:
        case error.POSITION_UNAVAILABLE:
        case error.TIMEOUT:
        case error.UNKNOWN_ERROR:
            $('#promptModal').modal('show')
            break;
    }
}