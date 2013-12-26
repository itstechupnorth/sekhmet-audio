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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.itstechupnorth.sekhmet.audio.io.AudioData;

public class AudioDataInputStream extends InputStream {

    private static final int EOF = -1;
    private ByteArrayInputStream delegate;
    private final Iterator<AudioData> dataIterator;

    public AudioDataInputStream(Iterator<AudioData> dataIterator) {
        super();
        this.dataIterator = dataIterator;
    }

    public void close() throws IOException {
        delegate.close();
    }

    public int read() throws IOException {
        if (capacity()) {
            final int result = delegate.read();
            return result;
        } else {
            return EOF;
        }
    }

    public int read(byte[] b, int off, int len) throws IOException {
        if (capacity()) {
            final int bytes = delegate.read(b, off, len);
            return bytes;
        } else {
            return EOF;
        }
    }

    public int read(byte[] b) throws IOException {
        if (capacity()) {
            final int bytes = delegate.read(b);
            return bytes;
        } else {
            return EOF;
        }
    }

    private boolean capacity() {
        if (delegate == null || delegate.available() <= 0) {
            if (dataIterator.hasNext()) {
                final AudioData data = dataIterator.next();
                delegate = data.inputStream();
            } else {
                return false;
            }
        }
        return true;
    }
}
