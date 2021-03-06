package com.google.android.youtube.player;

import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView.YouTubePlayerViewInitializer;
import com.google.android.youtube.player.utils.Validators;

/**
 * @deprecated Use {@link YouTubePlayerFragment} instead
 */
@Deprecated
public class YouTubePlayerSupportFragment extends Fragment implements Provider {

    public static final String KEY_PLAYER_VIEW_STATE = "YouTubePlayerSupportFragment.KEY_PLAYER_VIEW_STATE";

    private final YouTubePlayerSupportFragment.a a = new YouTubePlayerSupportFragment.a();
    private Bundle bundle;
    private YouTubePlayerView youTubePlayerView;
    private String developerKey;
    private OnInitializedListener onInitializedListener;
    private boolean f;

    @Override
    public void initialize(String developerKey, OnInitializedListener listener) {
        this.developerKey = Validators.notEmpty(developerKey, "Developer key cannot be null or empty");
        this.onInitializedListener = listener;
        this.a();
    }

    private void a() {
        if (this.youTubePlayerView != null && this.onInitializedListener != null) {
            this.youTubePlayerView.a(this.f);
            this.youTubePlayerView.initialize(this.getActivity(), this, this.developerKey, this.onInitializedListener, this.bundle);
            this.bundle = null;
            this.onInitializedListener = null;
        }
    }

    @Override
    public void onCreate(Bundle var1) {
        super.onCreate(var1);
        this.bundle = var1 != null ? var1.getBundle(KEY_PLAYER_VIEW_STATE) : null;
    }

    @Override
    public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
        this.youTubePlayerView = new YouTubePlayerView(this.getActivity(), (AttributeSet) null, 0, this.a);
        this.a();
        return this.youTubePlayerView;
    }

    @Override
    public void onStart() {
        super.onStart();
        this.youTubePlayerView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.youTubePlayerView.onResume();
    }

    @Override
    public void onPause() {
        this.youTubePlayerView.onPause();
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle currentState = this.youTubePlayerView != null ? this.youTubePlayerView.getBundle() : this.bundle;
        outState.putBundle(KEY_PLAYER_VIEW_STATE, currentState);
    }

    @Override
    public void onStop() {
        this.youTubePlayerView.onStop();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        this.youTubePlayerView.stopSelf(this.getActivity().isFinishing());
        this.youTubePlayerView = null;
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if (this.youTubePlayerView != null) {
            FragmentActivity activity = this.getActivity();
            this.youTubePlayerView.release(activity == null || activity.isFinishing());
        }

        super.onDestroy();
    }

    private final class a implements YouTubePlayerViewInitializer {
        private a() {
        }

        public final void initializeView(YouTubePlayerView view, String developerKey, OnInitializedListener listener) {
            YouTubePlayerSupportFragment.this.initialize(developerKey, YouTubePlayerSupportFragment.this.onInitializedListener);
        }

        public final void onViewInitialized(YouTubePlayerView view) {
        }
    }
}
