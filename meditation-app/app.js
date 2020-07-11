const app = () => {
    const song = document.querySelector('.song');
    const play = document.querySelector('.play');
    const outline = document.querySelector('.moving-outline circle');
    const video = document.querySelector('.vid-container video');

    //Sounds
    const sounds = document.querySelectorAll('.sound-picker button')
    //Time Select
    const timeSelect = document.querySelectorAll('.time-select button');
    //Time Display
    const timeDisplay = document.querySelector('.time-display');
    //Get the length of the outline
    const outlineLength = outline.getTotalLength();
    //Duration
    let fakeDuration = 600;

    outline.style.strokeDasharray = outlineLength;
    outline.style.strokeDashoffset = outlineLength;


    //Play sound
    play.addEventListener('click', () => {
        checkPlaying();
    });

    const checkPlaying = () => {
        //Function to stop and play the sounds
        if (song.paused) {
            song.play();
            video.play();
            play.src = './svg/pause.svg';
        } else {
            song.pause();
            video.pause();
            play.src = './svg/play.svg';
        }
    }

    sounds[0].addEventListener('click', () => {
        video.src = './video/rain.mp4';
        song.src = './sounds/rain.mp3';
        checkPlaying();
    })


    sounds[1].addEventListener('click', () => {
        video.src = './video/beach.mp4';
        song.src = './sounds/beach.mp3';
        checkPlaying();
    })

    timeSelect[1].addEventListener('click', () => {
        timeDisplay.innerHTML = "2:00";
    })


};

app();