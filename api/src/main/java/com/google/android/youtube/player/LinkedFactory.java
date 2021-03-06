package com.google.android.youtube.player;

import android.app.Activity;
import android.content.Context;

import com.google.android.youtube.player.impl.AbstractYouTubeThumbnailLoader;
import com.google.android.youtube.player.impl.LocallyLinkedFactory;
import com.google.android.youtube.player.impl.RemoteEmbeddedPlayer;
import com.google.android.youtube.player.internal.IEmbeddedPlayer;
import com.google.android.youtube.player.network.Client;
import com.google.android.youtube.player.network.Client.Connection;
import com.google.android.youtube.player.network.ConnectionClient;

public abstract class LinkedFactory {

    private static final LinkedFactory INSTANCE = instantiate();

    private static LinkedFactory instantiate() {
        if (INSTANCE != null) return INSTANCE;
        try {
            return (LinkedFactory) Class.forName("com.google.android.youtube.api.locallylinked.LocallyLinkedFactory")
                    .asSubclass(LinkedFactory.class).newInstance();
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (ClassNotFoundException e) {
            return new LocallyLinkedFactory();
        }
    }

    public static LinkedFactory getInstance() {
        return INSTANCE;
    }

    public abstract ConnectionClient getClient(Context context, String developerKey, Connection var3, Client.OnInitializationResult result);

    public abstract IEmbeddedPlayer getPlayer(Activity activity, ConnectionClient connectionClient, boolean var3) throws RemoteEmbeddedPlayer.RemotePlayerException;

    public abstract AbstractYouTubeThumbnailLoader getThumbnailLoader(ConnectionClient connectionClient, YouTubeThumbnailView thumbnail);
}
