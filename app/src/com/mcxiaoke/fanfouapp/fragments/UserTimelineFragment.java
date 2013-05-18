package com.mcxiaoke.fanfouapp.fragments;

import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import com.mcxiaoke.fanfouapp.api.Paging;
import com.mcxiaoke.fanfouapp.app.AppContext;
import com.mcxiaoke.fanfouapp.controller.DataController;
import com.mcxiaoke.fanfouapp.dao.model.StatusModel;
import com.mcxiaoke.fanfouapp.service.FanFouService;
import com.mcxiaoke.fanfouapp.ui.widget.ProfileView;
import com.mcxiaoke.fanfouapp.util.Utils;


/**
 * @author mcxiaoke
 * @version 1.3 2012.03.19
 */
public class UserTimelineFragment extends BaseTimlineFragment {
    private static final String TAG = UserTimelineFragment.class
            .getSimpleName();
    private String userId;

    public static UserTimelineFragment newInstance(String userId) {
        return newInstance(userId, false);
    }

    public static UserTimelineFragment newInstance(String userId,
                                                   boolean refresh) {
        Bundle args = new Bundle();
        args.putString("id", userId);
        args.putBoolean("refresh", refresh);
        UserTimelineFragment fragment = new UserTimelineFragment();
        fragment.setArguments(args);
        if (AppContext.DEBUG) {
            Log.d(TAG, "newInstance() " + fragment);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppContext.DEBUG) {
            Log.d(TAG, "onCreate() userId=" + userId);
        }
    }

    @Override
    protected void parseArguments(Bundle args) {
        if (args != null) {
            userId = args.getString("id");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        ListView listView = getListView();
        View view = new ProfileView(getActivity());
        listView.addHeaderView(view, null, false);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected int getType() {
        return StatusModel.TYPE_USER;
    }

    @Override
    protected void doFetch(boolean doGetMore) {
        final ResultHandler handler = new ResultHandler(this);
        final Cursor cursor = getCursor();

        Paging p = new Paging();
        if (doGetMore) {
            p.maxId = Utils.getMaxId(cursor);
        } else {
            p.sinceId = Utils.getSinceId(cursor);
        }
        if (AppContext.DEBUG) {
            Log.d(TAG, "doFetch() userId=" + userId + " doGetMore=" + doGetMore
                    + " paging=" + p + " type=" + getType());
        }
        FanFouService.getTimeline(getActivity(), getType(), handler, userId, p);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return DataController
                .getUserTimelineCursorLoader(getActivity(), userId);
    }

    @Override
    public String getTitle() {
        return "消息";
    }

}
