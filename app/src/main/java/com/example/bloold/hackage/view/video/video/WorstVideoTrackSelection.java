package com.example.bloold.hackage.view.video.video;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.trackselection.BaseTrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelection;


public class WorstVideoTrackSelection extends BaseTrackSelection {


    public static final class Factory implements TrackSelection.Factory {

        public Factory() {
        }

        @Override
        public WorstVideoTrackSelection createTrackSelection(TrackGroup group, int... tracks) {
            return new WorstVideoTrackSelection(group, tracks);
        }

    }

    public WorstVideoTrackSelection(TrackGroup group, int... tracks) {
        super(group, tracks);
    }

    @Override
    public int getSelectedIndex() {
        // last index has the stream with the lowest bitrate
        return getTrackGroup().length - 1;
    }

    @Override
    public int getSelectionReason() {
        return C.SELECTION_REASON_MANUAL;
    }

    @Override
    public Object getSelectionData() {
        return null;
    }

    @Override
    public void updateSelectedTrack(long playbackPositionUs, long bufferedDurationUs, long availableDurationUs) {

    }
}