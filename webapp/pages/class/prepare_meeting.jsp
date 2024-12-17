<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Meeting Interface</title>


</head>

<body>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<style>
    body {
        background-color: #f8f9fa;
    }

    .meeting-container {
        background-color: #2b2b2b;
        color: white;
        border-radius: 8px;
        padding: 20px;
        margin-top: 50px;
        width: 800px;
        height: 530px;
        position: relative;
        overflow: hidden;
    }
    #videoElement {
        width: 100%;
        height: 360px;
        background-color: black;
        border-radius: 8px;
        margin-top: 0;
    }

    .icon-btn {
        width: 48px;
        height: 48px;
        border-radius: 50%;
        display: inline-flex;
        justify-content: center;
        align-items: center;
        margin: 0px 40px 5px;
        padding: 10px;
        cursor: pointer;
        overflow: visible;
    }

    .icon-muted {
        background-color: rgb(234, 22, 22);
    }
    .icon-active {
        background-color: rgba(50, 130, 236, 0.92);
    }

    .join-section {
        margin-top: 20px;
        padding: 30px;
        margin-left: 40px;
    }
</style>
<div class="container d-flex justify-content-start">
    <div class="meeting-container text-center">
        <button class="btn btn-primary mb-4" onclick="enableDevices()">Cho phép sử dụng micrô và máy ảnh</button>
        <div id="videoContainer">
            <video id="videoElement" autoplay muted></video>
        </div>
        <div class="mt-4">
            <img src="/views/clients/assets/fonts/myself-icons/ic_mute.png" alt="Mute Mic" id="ic_mute"
                 class="icon-btn icon-muted" onclick="toggleMic()">
            <img src="/views/clients/assets/fonts/myself-icons/ic_mute_camera.png" alt="Mute Camera" id="ic_mute_cam"
                 class="icon-btn icon-muted" onclick="toggleCam()">
        </div>

    </div>

    <div class="col-md-4 join-section">
        <h3>Sẵn sàng tham gia?</h3>
        <p>Không có người nào khác ở đây</p>
        <div class="btn btn-primary mb-4" onclick="joinMeeting()">Tham gia ngay</div>
    </div>
</div>

<script src="../../assets/js/script.js"></script>
<script>
    let stream;
    let videoEnabled = false;
    let audioEnabled = false;
    let isPermittedvideo = false;
    let isPermittedaudio = false;
    function enableDevices() {
        navigator.mediaDevices.getUserMedia({ video: true, audio: true })
            .then(s => {
                stream = s;
                document.getElementById("videoElement").srcObject = stream;
                document.getElementById("ic_mute").classList.replace("icon-muted", "icon-active");
                document.getElementById("ic_mute_cam").classList.replace("icon-muted", "icon-active");
                console.log("Camera và micro đã được bật.");
                videoEnabled = true;
                audioEnabled = true;
                isPermittedvideo = true;
                isPermittedaudio = true;
                const camIcon = document.getElementById("ic_mute_cam");
                camIcon.src = "/views/clients/assets/fonts/myself-icons/ic_video_camera.png";
                const micIcon = document.getElementById("ic_mute");
                micIcon.src = "/views/clients/assets/fonts/myself-icons/ic_mic.png";
            })
            .catch(error => {
                alert("Bạn đã từ chối quyền truy cập camera hoặc micro. Vui lòng kiểm tra cài đặt.");
            });
    }

    function toggleCam() {
        videoEnabled = !videoEnabled;
        stream.getVideoTracks()[0].enabled = videoEnabled;
        const camIcon = document.getElementById("ic_mute_cam");
        if (videoEnabled) {
            camIcon.classList.replace("icon-muted", "icon-active");
            camIcon.src = "/views/clients/assets/fonts/myself-icons/ic_video_camera.png"; // Icon bật cam
        } else {
            camIcon.classList.replace("icon-active", "icon-muted");
            camIcon.src = "/views/clients/assets/fonts/myself-icons/ic_mute_camera.png"; // Icon tắt cam
        }
    }

    function toggleMic() {
        audioEnabled = !audioEnabled;
        stream.getAudioTracks()[0].enabled = audioEnabled;
        const micIcon = document.getElementById("ic_mute");
        if (audioEnabled) {
            micIcon.classList.replace("icon-muted", "icon-active");
            micIcon.src = "/views/clients/assets/fonts/myself-icons/ic_mic.png"; // Icon bật mic
        } else {
            micIcon.classList.replace("icon-active", "icon-muted");
            micIcon.src = "/views/clients/assets/fonts/myself-icons/ic_mute.png"; // Icon tắt mic
        }
    }

    function joinMeeting() {
        const meetingId = '<%= request.getAttribute("meetingId") %>';
        window.location.href = "/StartMeeting?meetingId=" + meetingId
            +"&videoEnabled=" + videoEnabled + "&audioEnabled=" + audioEnabled
            + "&isPermittedvideo=" + isPermittedvideo + "&isPermittedaudio=" + isPermittedaudio;
    }
</script>
</body>

</html>
    