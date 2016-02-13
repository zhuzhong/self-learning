package com.behaviour.command.demo;
public class PlayCommand implements Command {

    private AudioPlayer myAudio;
    
    public PlayCommand(AudioPlayer audioPlayer){
        myAudio = audioPlayer;
    }
    /**
     * Ö´ÐÐ·½·¨
     */
    @Override
    public void execute() {
        myAudio.play();
    }

}