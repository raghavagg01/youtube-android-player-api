package com.google.android.youtube.player;

import android.app.Activity;
import android.content.Context;

import com.google.android.youtube.player.internal.t.a;

public final class ac extends aa {
    public ac() {
    }

    public final b a(Context var1, String var2, a var3, com.google.android.youtube.player.internal.t.b var4) {
        return new o(var1, var2, var1.getPackageName(), z.d(var1), var3, var4);
    }

    public final d a(Activity var1, b var2, boolean var3) throws com.google.android.youtube.player.internal.w.a {
        return w.a(var1, var2.a(), var3);
    }

    public final com.google.android.youtube.player.internal.a a(b var1, YouTubeThumbnailView var2) {
        return new p(var1, var2);
    }
}
