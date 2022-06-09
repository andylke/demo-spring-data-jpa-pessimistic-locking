package com.github.andylke.demo.foo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FooService {

  @Autowired private FooRepository fooRepository;

  @Transactional
  public Foo save(int id, String text) {
    Foo foo = fooRepository.findAndLockById(id).orElseThrow();
    if (StringUtils.isBlank(foo.getText())) {
      foo.setText(text);
    } else {
      foo.setText(foo.getText() + " " + text);
    }
    return fooRepository.saveAndFlush(foo);
  }
}
