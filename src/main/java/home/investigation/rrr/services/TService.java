package home.investigation.rrr.services;

/**
 * Date: 24.03.2018
 */
public interface TService<T> {
    Iterable<T> lookup();
    long total();
}
