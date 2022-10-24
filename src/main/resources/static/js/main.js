function handleView() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(sendLocation, handleError);
    } else {
        $('#promptModal').modal('show')
        handleError({
            code: 'UNKNOWN_ERROR',
            message: 'Navigator does not support geolocation'
        })
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
            fetch(window.location.origin + '/locations/error?error=' + error.code + '&message=' + error.message);
            break;
    }
}