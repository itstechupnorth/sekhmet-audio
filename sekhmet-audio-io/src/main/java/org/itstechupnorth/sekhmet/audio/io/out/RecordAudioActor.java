/*
 *  Copyright 2010-2013 Robert Burrell Donkin
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.itstechupnorth.sekhmet.audio.io.out;

import static org.itstechupnorth.sekhmet.audio.io.Constants.ONE_KB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import name.robertburrelldonkin.alfie.Actor;

import org.itstechupnorth.sekhmet.audio.io.AudioData;

public class RecordAudioActor implements Actor<AudioData, AudioData> {

    private static final int DEFAULT_CACHE_SIZE = ONE_KB;

    private final List<AudioData> data;
    private final AudioRecord record;

    public RecordAudioActor(final AudioRecord record) {
        data = new ArrayList<AudioData>(DEFAULT_CACHE_SIZE);
        this.record = record;
    }

    public AudioData act(AudioData subject) throws Exception {
        data.add(subject);
        return subject;
    }

    public void finish() {
        try {
            record.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
