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
package org.itstechupnorth.sekhmet.audio.io.in;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import name.robertburrelldonkin.alfie.Message;
import name.robertburrelldonkin.alfie.QueuingSource;

import org.itstechupnorth.sekhmet.audio.io.AudioData;

class AudioReaderSource extends QueuingSource<AudioData> implements Runnable {

    final AudioReader reader;

    public AudioReaderSource(BlockingQueue<Message<AudioData>> out,
            final AudioReader reader) {
        super(out);
        this.reader = reader;
    }

    public void open() {
        new Thread(this).start();
    }

    public void run() {
        boolean open;
        try {
            reader.open();
            open = true;
        } catch (IOException e) {
            fatal(e);
            open = false;
        }
        while (open) {
            open = read();
        }
    }

    private boolean read() {
        try {
            final AudioData audio = reader.read();
            if (audio == null) {
                close();
                return false;
            } else {
                output(audio);
                return true;
            }
        } catch (IOException e) {
            fatal(e);
            return false;
        }
    }

    @Override
    protected void hookForClose() {
        super.hookForClose();
        reader.close();
    }

}