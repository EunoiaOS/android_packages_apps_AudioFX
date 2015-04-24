/*
 * Copyright (C) 2014 The CyanogenMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cyngn.audiofx.preset;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cyngn.audiofx.R;
import com.cyngn.audiofx.activity.MasterConfigControl;

public class PresetPagerAdapter extends PagerAdapter {

    Context mContext;
    MasterConfigControl mConfig;

    public PresetPagerAdapter(Context context) {
        super();
        mContext = context;
        mConfig = MasterConfigControl.getInstance(mContext);
    }

    @Override
    public int getItemPosition(Object object) {
        View v = (View) object;
        int index = mConfig.indexOf(((MasterConfigControl.Preset) v.getTag()));
        if (index == -1) {
            return POSITION_NONE;
        } else {
            return index;
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.preset_adapter_row, container, false);
        TextView tv = (TextView) view;
        tv.setText(mConfig.getLocalizedPresetName(position));
        tv.setTag(mConfig.getPreset(position));
        container.addView(tv);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object instanceof View) {
            container.removeView((View) object);
        }
    }

    @Override
    public int getCount() {
        return mConfig.getPresetCount();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }



}
