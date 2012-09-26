package parser

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel

class StreamWrapper {

    def final FileChannel channel

    public StreamWrapper(FileInputStream stream) {
        this.channel = stream.getChannel()
    }

    public byte read() {
        ByteBuffer bb = ByteBuffer.allocate(1)
        channel.read(bb)
        bb.array()[0]
    }

    public byte[] getBytes(numberOfBytes) {
        ByteBuffer bb = ByteBuffer.allocate(numberOfBytes)
        channel.read(bb);
        bb.array()
    }

    public readMarker() {
        def data = getBytes(2)
        assert data[0] == (byte) 0xff
        def code = MarkerCodeAssignments.getValue(data[1])
        switch (code) {
            case MarkerCodeAssignments.Start_Of_Image:
                return [code, 0]
            default:
                return [code, readUInt(2)]
        }
    }

    public readUInt(int numberOfBytes) {
        def data = getBytes(numberOfBytes)
        def val = 0
        numberOfBytes.times {
            def bitShift = ((numberOfBytes - 1 - it) * 8)
            val += (data[it] & 0xff) << bitShift
        }
        val
    }

    public skip(long bytes) {
        channel.position(channel.position() + bytes)
    }

    public position() {
        channel.position()
    }
}
