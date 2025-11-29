import pytest
from my_code import is_prime, factorial


def test_is_prime_small_primes():
    assert is_prime(2)
    assert is_prime(3)


def test_is_prime_nonprimes():
    assert not is_prime(0)
    assert not is_prime(1)
    assert not is_prime(4)


def test_factorial_basic():
    assert factorial(0) == 1
    assert factorial(1) == 1
    assert factorial(5) == 120


def test_factorial_negative():
    with pytest.raises(ValueError):
        factorial(-1)
