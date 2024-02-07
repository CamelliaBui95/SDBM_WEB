package fr.btn.sdbm_web.metier;

import java.util.Objects;

public class Volume {
    private int volume;

    public Volume() {
        volume = 0;
    }

    public Volume(int volume) {
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volume volume1 = (Volume) o;
        return volume == volume1.volume;
    }

    @Override
    public int hashCode() {
        return Objects.hash(volume);
    }
}
